package com.fooddelivery.service;

import com.fooddelivery.model.DeliveryCompany;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface DeliveryCompanyService {

    List<DeliveryCompany> getAll();

    DeliveryCompany create(@NotNull DeliveryCompany deliveryCompany);

    DeliveryCompany getById(@NotNull Long id);

    void deleteById(@NotNull Long id);

    DeliveryCompany update(@NotNull DeliveryCompany deliveryCompany);
}
