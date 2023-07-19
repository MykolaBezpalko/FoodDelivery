package com.fooddelivery.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fooddelivery.model.OrderStatus;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class OrderRequestDTO {

    private String orderName;

    @NotNull
    private Long establishmentId;

    @NotNull
    private Long deliveryCompanyId;

    private OrderStatus orderStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endOfOrder;

}
