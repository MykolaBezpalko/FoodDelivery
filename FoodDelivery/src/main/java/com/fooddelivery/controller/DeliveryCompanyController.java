package com.fooddelivery.controller;

import com.fooddelivery.model.DeliveryCompany;
import com.fooddelivery.service.DeliveryCompanyService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/delivery-company")
public class DeliveryCompanyController {

    @Resource
    private DeliveryCompanyService deliveryCompanyService;

    @GetMapping
    public List<DeliveryCompany> getAll() {
        return deliveryCompanyService.getAll();
    }

    @GetMapping("/{id}")
    public DeliveryCompany getById(@PathVariable("id") @NotNull Long id) {
        return deliveryCompanyService.getById(id);
    }

    @PostMapping
    public DeliveryCompany create(@RequestBody @Valid DeliveryCompany deliveryCompany) {
        return deliveryCompanyService.create(deliveryCompany);
    }

    @PutMapping
    public DeliveryCompany update(@RequestBody @Valid DeliveryCompany deliveryCompany) {
        return deliveryCompanyService.update(deliveryCompany);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") @NotNull Long id) {
        deliveryCompanyService.deleteById(id);
    }

}
