package com.fooddelivery.dto.response;

import com.fooddelivery.model.Dish;
import com.fooddelivery.model.Status;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DishResponse {

    private Long dishId;

    private String dishName;

    private Double dishCost;

    private String description;

    private String imageLink;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Status status;

    private Long establishmentId;

    public static DishResponse convertFromEntity(Dish dish) {
        DishResponse response = new DishResponse();
        response.dishId = dish.getDishId();
        response.dishName = dish.getDishName();
        response.dishCost = dish.getDishCost();
        response.description = dish.getDescription();
        response.imageLink = dish.getImageLink();
        response.createDate = dish.getCreateDate();
        response.updateDate = dish.getUpdateDate();
        response.establishmentId = dish.getEstablishment().getEstablishmentId();
        return response;
    }
}
