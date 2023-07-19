package com.fooddelivery.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fooddelivery.model.Establishment;
import com.fooddelivery.model.Order;
import com.fooddelivery.model.OrderStatus;
import com.fooddelivery.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderResponseDTO {

    private Long orderId;

    private Status status;

    private OrderStatus orderStatus;

    private String orderName;

    private Double deliveryCost;

    private Double orderSum;

    private String userEmail;

    private DeliveryCompanyResponse deliveryCompany;

    private Establishment establishment;

    private List<OrderLineItemResponseDTO> orderLineItems;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endOfOrder;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateDate;


    public static OrderResponseDTO convertFromEntity(Order order) {
        OrderResponseDTO response = new OrderResponseDTO();
        response.orderId = order.getOrderId();
        response.orderStatus = order.getOrderStatus();
        response.orderName = order.getOrderName();
        response.deliveryCost = order.getDeliveryCost();
        response.userEmail = order.getUserEmail();
        response.deliveryCompany = DeliveryCompanyResponse.convertFromEntity(order.getDeliveryCompany());
        response.establishment = order.getEstablishment();
        response.endOfOrder = order.getEndOfOrder();
        response.createDate = order.getCreateDate();
        response.updateDate = order.getUpdateDate();

        if (order.getOrderLineItems() != null) {
            response.orderLineItems = order.getOrderLineItems()
                    .stream()
                    .map(OrderLineItemResponseDTO::convertFromEntity)
                    .collect(Collectors.toList());

            response.orderSum = response.orderLineItems
                    .stream()
                    .mapToDouble(OrderLineItemResponseDTO::getOrderLineItemSum)
                    .sum();
        } else {
            response.orderSum = 0.0;
        }
        return response;
    }

}
