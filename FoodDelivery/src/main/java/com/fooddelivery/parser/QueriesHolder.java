package com.fooddelivery.parser;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@PropertySource("classpath:parser_elements.properties")
@Component
@NoArgsConstructor
public class QueriesHolder {
    @Value("${parser.restaurants-item-query}")
    private String restaurantsItemQuery;
    public static String restaurantsItem;

    @Value("${parser.restaurant-name-query}")
    private String restaurantNameQuery;
    public static String restaurantName;

    @Value("${parser.restaurant-rate-query}")
    private String restaurantRateQuery;
    public static String restaurantRate;

    @Value("${parser.restaurant-delivery-cost-query}")
    private String restaurantDeliveryCostQuery;
    public static String restaurantDeliveryCost;

    @Value("${parser.restaurant-image-link-query}")
    private String restaurantImageLinkQuery;
    public static String restaurantImageLink;

    @Value("${parser.restaurant-item-containers-query}")
    private String restaurantItemContainersQuery;
    public static String restaurantItemContainers;

    @Value("${parser.items-name-query}")
    private String itemNameQuery;
    public static String itemName;

    @Value("${parser.items-price-query}")
    private String itemPriceQuery;
    public static String itemPrice;

    @Value("${parser.items-description-query}")
    private String itemDescriptionQuery;
    public static String itemDescription;

    @Value("${parser.items-image-link-query}")
    private String itemImageLinkQuery;
    public static String itemImageLink;

    @Value("${parser.text-pages-query}")
    private String textPagesQuery;
    public static String textPages;

    @PostConstruct
    public void initStaticFields() {
        restaurantsItem = this.restaurantsItemQuery;
        restaurantName = this.restaurantNameQuery;
        restaurantRate = this.restaurantRateQuery;
        restaurantDeliveryCost = this.restaurantDeliveryCostQuery;
        restaurantImageLink = this.restaurantImageLinkQuery;
        restaurantItemContainers = this.restaurantItemContainersQuery;
        itemName = this.itemNameQuery;
        itemPrice = this.itemPriceQuery;
        itemDescription = this.itemDescriptionQuery;
        itemImageLink = this.itemImageLinkQuery;
        textPages = this.textPagesQuery;
    }
}
