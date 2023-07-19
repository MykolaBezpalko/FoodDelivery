package com.fooddelivery.parser;

import com.fooddelivery.model.Dish;
import com.fooddelivery.model.Establishment;
import com.fooddelivery.parser.connector.JsoupConnector;
import com.fooddelivery.parser.selectorExecutors.RestaurantSelectorExecutor;
import com.fooddelivery.parser.writer.EstablishmentWriterToDB;
import com.fooddelivery.utils.TimeDurationFinder;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Component parser class that uses for handle parse operation
 * Uses {@link com.fooddelivery.parser.writer.EstablishmentWriterToDB} class
 * <p>
 * Workflow: init parser class -> parse count of pages -> parse each page -> parse each establishment
 */
@Component
@Slf4j
public class Parser {

    /**
     * The value of RESTAURANTS_LINK is {@value}
     */
    public static final String RESTAURANTS_LINK = "/ua/uk/ivano-frankivsk/restorani_1/";

    @Resource
    private EstablishmentWriterToDB establishmentWriterToDB;

    /**
     * Init method to start parse operation: from start of parsing to writing to files
     */
    public void parse() {
        TimeDurationFinder durationFinder = new TimeDurationFinder().init();
        log.info("Start parsing!...");

        RestaurantSelectorExecutor selectorsExecutor =
                new RestaurantSelectorExecutor(JsoupConnector.getConnection(RESTAURANTS_LINK));

        Map<String, Establishment> menus = new HashMap<>();

        int numberOfPages = parseNumberOfPages(selectorsExecutor);
        log.info("Count of pages = " + numberOfPages);
        numberOfPages = 1;

        for (int pageNumber = 0; pageNumber < numberOfPages; pageNumber++) {
            menus.putAll(parseOnePage(pageNumber + 1));
        }
        log.info("Writing to the database!..");
        establishmentWriterToDB.outToDB(menus);

        log.info("Wrote! Parsing has been ended! Time spent: "
                + durationFinder.end().findDifference(ChronoUnit.SECONDS) + " seconds");
    }

    /**
     * Method that used to parse number of pages with restaurants from main page
     *
     * @param selectorsExecutor {@link RestaurantSelectorExecutor}
     * @return number {@link Integer} number of pages
     */
    private static Integer parseNumberOfPages(RestaurantSelectorExecutor selectorsExecutor) {
        try {
            String pagesText = selectorsExecutor.getTextBySelector(QueriesHolder.textPages);
            return Integer.parseInt(pagesText.substring(pagesText.indexOf("із") + 3));
        } catch (NumberFormatException e) {
            log.error("* ~ * Failed to parse number of pages * ~ *");
            return 0;
        } catch (IllegalArgumentException e) {
            log.info("* ~ * Not found info about pages, returning 1 * ~ *");
            return 1;
        }
    }

    /**
     * Method that parse the whole of page using the number of page <br>
     * using method {@link #parseAllMenus(List)}
     *
     * @param pageNumber current index of page
     * @return {@link Map} where key is name of restaurant and value {@link Establishment}
     */
    private static Map<String, Establishment> parseOnePage(int pageNumber) {
        Document document = JsoupConnector
                .getConnection(RESTAURANTS_LINK + (pageNumber == 1 ? "" : "?page=" + pageNumber));
        Map<String, Establishment> result = new HashMap<>();

        if (document != null) {
            RestaurantSelectorExecutor selectorsExecutor =
                    new RestaurantSelectorExecutor(document);
            log.debug("Parsing " + pageNumber + " page!...");
            result = parseAllMenus(selectorsExecutor.getLinksToAllRestaurants());
        }
        return result;
    }

    /**
     * Method that parse each of restaurants by {@link List} of links <br>
     * using method {@link #parseOneMenu(String)} for each link
     *
     * @param linksToRestaurants {@link List} links to all restaurants of current page
     * @return {@link Map} where key is name of restaurant and value {@link Establishment}
     */
    private static Map<String, Establishment> parseAllMenus(List<String> linksToRestaurants) {
        Map<String, Establishment> menusLocal = new HashMap<>();

        linksToRestaurants.forEach(link -> {
            parseOneMenu(link).ifPresent(establishment -> menusLocal.put(link, establishment));
        });

        return menusLocal;
    }

    /**
     * Method that parse the whole menu by link to a restaurant <br>
     * Using method {@link RestaurantSelectorExecutor#getAllDishes(Establishment)
     * RestaurantSelectorExecutor.getAllDishes()} for each link
     *
     * @param linkToMenu String value of link after base on the glovo site
     * @return {@link Establishment Establishment} of current link
     */
    private static Optional<Establishment> parseOneMenu(String linkToMenu) {
        log.debug("Parsing " + linkToMenu + " menu!...");

        RestaurantSelectorExecutor restaurantSelectorExecutor = new RestaurantSelectorExecutor(linkToMenu);
        Establishment establishment = null;

        if (Objects.nonNull(restaurantSelectorExecutor.getDocument())) {
            String name = restaurantSelectorExecutor.getName();
            Double deliveryCost = restaurantSelectorExecutor.getDeliveryCost();
            int ratePercent = restaurantSelectorExecutor.getRate();
            String imageLink = restaurantSelectorExecutor.getImageLink();

            establishment = Establishment.builder()
                    .establishmentName(name)
                    .deliveryCost(deliveryCost)
                    .ratePercent(ratePercent)
                    .imageLink(imageLink)
                    .build();

            Set<Dish> dishes = restaurantSelectorExecutor.getAllDishes(establishment);
            establishment.setDishes(dishes);
        }
        return Optional.ofNullable(establishment);
    }

    /**
     * Method to parse string with price to double number of price
     *
     * @param stringOfPrice - String value of price that shown on site (etc. 12,93$)
     * @return price in {@link Double} format
     */
    public static Double parsePrice(String stringOfPrice) {
        if (stringOfPrice == null || stringOfPrice.isEmpty())
            return null;
        if (stringOfPrice.length() - stringOfPrice.replaceAll("\\.", "").length() > 1)
            return null;
        return Double.parseDouble(stringOfPrice.replaceAll("[^\\d,]", "")
                .replace(',', '.'));
    }

}
