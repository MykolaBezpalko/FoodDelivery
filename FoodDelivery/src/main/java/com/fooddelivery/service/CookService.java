package com.fooddelivery.service;

import com.fooddelivery.model.Cook;

import java.util.List;

public interface CookService {

    List<Cook> getAll();

    Cook getById(Integer id);

    Cook save(Cook cook);

    Cook deleteById(Integer id);
}
