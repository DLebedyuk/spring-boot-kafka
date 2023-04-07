package com.dlebedyuk.menuservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
public class BusinessLaunch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    private Set<Dish> dishes;
    private String name;
    private BigDecimal amount;

}
