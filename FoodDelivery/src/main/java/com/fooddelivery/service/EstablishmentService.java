package com.fooddelivery.service;

import com.fooddelivery.model.Establishment;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface EstablishmentService {

    List<Establishment> getAll();

    Establishment create(@NotNull Establishment establishment);

    Establishment getById(@NotNull Long id);

    void deleteById(@NotNull Long id);

    Establishment update(@NotNull Establishment establishment);

}
