package com.openedsource.pizzastore.database.repository;


import com.openedsource.pizzastore.database.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza,Integer> {
}
