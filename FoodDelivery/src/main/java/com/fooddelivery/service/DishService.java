package com.fooddelivery.service;

import com.fooddelivery.model.Dish;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface DishService {

    List<Dish> getAll();

    Dish create(@NotNull Dish dish);

    Dish getById(@NotNull Long id);

    void deleteById(@NotNull Long id);

    Dish update(@NotNull Dish dish);

    List<Dish> getByEstablishmentId(@NotNull Long id);
}
