package com.fooddelivery.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DishItemResponse {

    private DishResponse dishResponse;
    private Integer count;

}
