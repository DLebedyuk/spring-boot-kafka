package com.dlebedyuk.menuservice.controller;

import com.dlebedyuk.menuservice.model.Dish;
import com.dlebedyuk.menuservice.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @GetMapping("/dishes")
    public List<Dish> getAllDishes() {
        return menuService.getAllDishes();
    }

    @PostMapping("/dishes")
    public Dish createDish(@RequestBody Dish dish) {
        Dish createdDish =  menuService.createDish(dish);
        kafkaTemplate.send("topic1", createdDish);
        return createdDish;
    }

    @PutMapping("/dishes/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable Long id, @RequestBody Dish dish) {
        dish.setId(id);
        Optional<Dish> updatedDish = menuService.updateDish(dish);
        return updatedDish.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/dishes/{id}")
    public ResponseEntity<Void> deleteDishById(@PathVariable Long id) {
        Optional<Boolean> result = menuService.deleteDishById(id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
