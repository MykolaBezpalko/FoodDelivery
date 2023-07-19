package com.fooddelivery.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fooddelivery.model.OrderLineItem;
import com.fooddelivery.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderLineItemResponseDTO {


    private Long orderLineItemId;

    private List<DishItemResponse> dishItems;

    private String userEmail;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateDate;

    private Status status;

    private Double orderLineItemSum;

    public static OrderLineItemResponseDTO convertFromEntity(OrderLineItem orderLineItem) {
        OrderLineItemResponseDTO response = new OrderLineItemResponseDTO();
        response.orderLineItemId = orderLineItem.getOrderLineItemId();
        response.userEmail = orderLineItem.getUserEmail();
        response.createDate = orderLineItem.getCreateDate();
        response.updateDate = orderLineItem.getUpdateDate();
        response.status = orderLineItem.getStatus();
        response.dishItems = orderLineItem.getDishCountMap()
                .entrySet()
                .stream()
                .map(e -> new DishItemResponse(
                        DishResponse.convertFromEntity(e.getKey()),
                        e.getValue()))
                .collect(Collectors.toList());

        response.orderLineItemSum = response.dishItems.stream().mapToDouble(i -> i.getDishResponse().getDishCost() * i.getCount()).sum();

        return response;
    }
}
