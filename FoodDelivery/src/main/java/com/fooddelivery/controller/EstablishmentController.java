package com.fooddelivery.controller;

import com.fooddelivery.model.Establishment;
import com.fooddelivery.service.EstablishmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/establishments")
public class EstablishmentController {

    @Resource
    private EstablishmentService establishmentService;

    @GetMapping
    public List<Establishment> getAll() {
        return establishmentService.getAll();
    }
}
