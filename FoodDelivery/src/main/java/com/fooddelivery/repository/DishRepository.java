package com.fooddelivery.repository;

import com.fooddelivery.model.Dish;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends PagingAndSortingRepository<Dish, Long> {

    Optional<Dish> findByDishNameAndEstablishmentEstablishmentId(String dishName, Long establishmentId);

    List<Dish> findAllByEstablishmentEstablishmentId(Long id);

}
