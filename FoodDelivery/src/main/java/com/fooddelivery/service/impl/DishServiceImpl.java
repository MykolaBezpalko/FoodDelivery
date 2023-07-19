package com.fooddelivery.service.impl;

import com.fooddelivery.model.Dish;
import com.fooddelivery.model.Status;
import com.fooddelivery.repository.DishRepository;
import com.fooddelivery.service.DishService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {

    @Resource
    private DishRepository dishRepository;

    @Override
    public List<Dish> getAll() {
        return Streamable.of(dishRepository.findAll()).toList();
    }

    @Override
    public Dish create(Dish dish) {
        Optional<Dish> dishOptional = dishRepository.findByDishNameAndEstablishmentEstablishmentId(dish.getDishName(),
                dish.getEstablishment().getEstablishmentId());

        if (dishOptional.isPresent()) {
            dish.setDishId(dishOptional.get().getDishId());
            return this.update(dish);
        }

        dish.setCreateDate(LocalDateTime.now());
        dish.setUpdateDate(LocalDateTime.now());
        dish.setStatus(Status.ACTIVE);
        return dishRepository.save(dish);
    }

    @Override
    public Dish getById(Long id) {
        return dishRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        Optional<Dish> dishOptional = dishRepository.findById(id);
        if (dishOptional.isPresent()) {
            Dish oldDish = dishOptional.get();
            oldDish.setStatus(Status.DELETED);
            oldDish.setUpdateDate(LocalDateTime.now());
            dishRepository.save(oldDish);
        }
    }

    @Override
    public Dish update(Dish dish) {
        dish.setUpdateDate(LocalDateTime.now());
        return dishRepository.save(dish);
    }

    @Override
    public List<Dish> getByEstablishmentId(Long id) {
        return dishRepository.findAllByEstablishmentEstablishmentId(id);
    }
}
