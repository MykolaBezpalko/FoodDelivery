package com.fooddelivery.service.impl;

import com.fooddelivery.model.DeliveryCompany;
import com.fooddelivery.model.Status;
import com.fooddelivery.repository.DeliveryCompanyRepository;
import com.fooddelivery.service.DeliveryCompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryCompanyServiceImpl implements DeliveryCompanyService {

    @Resource
    DeliveryCompanyRepository deliveryCompanyRepository;

    @Override
    public List<DeliveryCompany> getAll() {
        return deliveryCompanyRepository.findAll();
    }

    @Override
    public DeliveryCompany create(@NotNull DeliveryCompany deliveryCompany) {
        deliveryCompany.setCreateDate(LocalDateTime.now());
        deliveryCompany.setUpdateDate(LocalDateTime.now());
        deliveryCompany.setStatus(Status.ACTIVE);
        return deliveryCompanyRepository.save(deliveryCompany);
    }

    @Override
    public DeliveryCompany getById(@NotNull Long id) {
        return deliveryCompanyRepository.findById(id).get();
    }

    @Override
    public void deleteById(@NotNull Long id) {
        Optional<DeliveryCompany> deliveryCompanyOptional = deliveryCompanyRepository.findById(id);
        if (deliveryCompanyOptional.isPresent()) {
            DeliveryCompany oldDeliveryCompany = deliveryCompanyOptional.get();
            oldDeliveryCompany.setStatus(Status.DELETED);
            oldDeliveryCompany.setUpdateDate(LocalDateTime.now());
            deliveryCompanyRepository.save(oldDeliveryCompany);
        }
    }

    @Override
    public DeliveryCompany update(@NotNull DeliveryCompany deliveryCompany) {
        deliveryCompany.setUpdateDate(LocalDateTime.now());
        return deliveryCompanyRepository.save(deliveryCompany);
    }
}
