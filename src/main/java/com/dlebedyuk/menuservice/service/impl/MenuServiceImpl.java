package com.dlebedyuk.menuservice.service.impl;

import com.dlebedyuk.menuservice.model.Dish;
import com.dlebedyuk.menuservice.repository.DishRepository;
import com.dlebedyuk.menuservice.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

    private final DishRepository dishRepository;

    @Override
    public Dish createDish(Dish dish) {
       return dishRepository.save(dish);
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Optional<Dish> updateDish(Dish dish) {
        Optional<Dish> existingDish = dishRepository.findById(dish.getId());
        if (existingDish.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(dishRepository.save(dish));
    }

    @Override
    public Optional<Boolean> deleteDishById(Long id) {
        Optional<Dish> existingDish = dishRepository.findById(id);
        if (existingDish.isEmpty()) {
            return Optional.empty();
        }
        dishRepository.deleteById(id);
        return Optional.of(true);
    }
}
