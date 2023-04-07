package com.dlebedyuk.menuservice.service;

import com.dlebedyuk.menuservice.model.Dish;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    Dish createDish(Dish dish);
    List<Dish> getAllDishes();

    Optional<Dish> updateDish(Dish dish);
    Optional<Boolean> deleteDishById(Long id);
}
