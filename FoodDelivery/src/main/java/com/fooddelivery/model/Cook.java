package com.fooddelivery.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Cook {

    @NonNull
    private Integer cookId;
    @NonNull
    private String name;
}
