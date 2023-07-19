package com.fooddelivery.parser.selectorExecutors;

import com.fooddelivery.model.Dish;
import com.fooddelivery.model.Establishment;
import com.fooddelivery.parser.Parser;
import com.fooddelivery.parser.QueriesHolder;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Specific class to execute queries to get specific restaurant elements from Jsoup document
 * extends behaviour of SelectorsExecutor so can execute simple queries
 */
@Slf4j
public class RestaurantSelectorExecutor extends SelectorsExecutor {
    private String linkToMenu;

    public RestaurantSelectorExecutor(@NotNull Document document) {
        super(document);
    }

    public RestaurantSelectorExecutor(@NotBlank String linkAfterBase) {
        super(linkAfterBase);
        this.linkToMenu = linkAfterBase;
    }

    /**
     * @return {@link List} of links to all restaurants from page
     */
    public List<String> getLinksToAllRestaurants() {
        return getAllElementsBySelector(QueriesHolder.restaurantsItem)
                .stream()
                .map(e -> e.attr("href"))
                .collect(Collectors.toList());
    }

    /**
     * @return {@link String} name of restaurant from the glovo page
     */
    public String getName() {
        try {
            return this.getTextBySelector(QueriesHolder.restaurantName);
        } catch (NullPointerException e) {
            log.error("Exception getName() on restaurant: " + linkToMenu);
            return "";
        }
    }

    /**
     * @return {@link Integer} rate of restaurant from the glovo page
     */
    public Integer getRate() {
        try {
            String rateStr = this.getTextBySelector(QueriesHolder.restaurantRate)
                    .replaceAll("\\D", "");
            if (rateStr.isEmpty())
                return 0;
            return Integer.parseInt(rateStr);
        } catch (NullPointerException e) {
            log.warn("Exception getRate() on restaurant: " + linkToMenu);
            return 0;
        }
    }

    /**
     * @return {@link Double} rate of restaurant from the glovo page
     */
    public Double getDeliveryCost() {
        try {
            return Parser.parsePrice(this.getTextBySelector(QueriesHolder.restaurantDeliveryCost));
        } catch (NullPointerException e) {
            log.warn("Exception getDeliveryCost() on restaurant: " + linkToMenu);
            return 0.0;
        }
    }

    /**
     * @return {@link String} link to image of restaurant from the glovo page
     */
    public String getImageLink() {
        try {
            return this.getAllElementsBySelector(QueriesHolder.restaurantImageLink).first().attr("src");
        } catch (NullPointerException e) {
            log.warn("Exception getImageLink() on restaurant: " + linkToMenu);
            return "";
        }
    }

    /**
     * Method that execute method {@link #getAllElementsBySelector(String)} and collect it to {@link Set}
     *
     * @param establishment {@link Establishment} to write this object to dishes as parent object
     * @return {@link Set} of {@link Dish dishes} of current {@link Establishment establishment}
     */
    public Set<Dish> getAllDishes(Establishment establishment) {
        Elements itemContainers = this.getAllElementsBySelector(QueriesHolder.restaurantItemContainers);
        return itemContainers.stream()
                .map(this::getDishFromContainer)
                .filter(Objects::nonNull)
                .peek(dish -> dish.setEstablishment(establishment))
                .collect(Collectors.toSet());
    }

    /**
     * Method that extract {@link Dish dishes} from one item container
     *
     * @param container {@link Element} that uses as container for {@link Dish}
     * @return {@link Dish} object from container
     */
    private Dish getDishFromContainer(Element container) {
        String dishName = container.select(QueriesHolder.itemName).text();
        if (dishName == null || dishName.isEmpty()) {
            return null;
        }
        Double dishCost = Parser.parsePrice(getTextBySelector(container, QueriesHolder.itemPrice));
        if (dishCost == null) {
            return null;
        }
        String description = container.select(QueriesHolder.itemDescription).text();
        String imageLink = container.select(QueriesHolder.itemImageLink).attr("src");
        return Dish.builder()
                .dishName(dishName)
                .description(description)
                .dishCost(dishCost)
                .imageLink(imageLink)
                .build();
    }
}
