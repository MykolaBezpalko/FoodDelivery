package com.fooddelivery.dto.response;

import com.fooddelivery.model.DeliveryCompany;
import com.fooddelivery.model.Status;

import java.time.LocalDateTime;

public class DeliveryCompanyResponse {

    private Long deliveryCompanyId;

    private Status status;

    private String deliveryCompanyName;

    private String imageLink;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public static DeliveryCompanyResponse convertFromEntity(DeliveryCompany deliveryCompany) {
        DeliveryCompanyResponse response = new DeliveryCompanyResponse();
        response.deliveryCompanyId = deliveryCompany.getDeliveryCompanyId();
        response.status = deliveryCompany.getStatus();
        response.deliveryCompanyName = deliveryCompany.getDeliveryCompanyName();
        response.imageLink = deliveryCompany.getImageLink();
        response.createDate = deliveryCompany.getCreateDate();
        response.updateDate = deliveryCompany.getUpdateDate();
        return response;
    }
}
