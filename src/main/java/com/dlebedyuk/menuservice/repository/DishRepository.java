package com.dlebedyuk.menuservice.repository;

import com.dlebedyuk.menuservice.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
