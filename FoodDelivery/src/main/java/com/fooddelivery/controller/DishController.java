package com.fooddelivery.controller;

import com.fooddelivery.model.Dish;
import com.fooddelivery.service.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {

    @Resource
    private DishService dishService;

    @GetMapping
    public List<Dish> getAll() {
        return dishService.getAll();
    }

    @GetMapping("/establishment/{id}")
    public ResponseEntity<List<Dish>> getByEstablishmentId(@PathVariable Long id) {
        return ResponseEntity.ok(dishService.getByEstablishmentId(id));
    }
}
