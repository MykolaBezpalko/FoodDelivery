package com.fooddelivery.dto.request;

import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Getter
public class OrderLineItemRequestDTO {

    @NotNull
    @Min(1)
    private Long orderLineItemId;

    @Size(min = 1)
    @NotEmpty
    private Map<Long, Integer> dishesIdCountMap;

}
