package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.Topping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToppingRepository extends JpaRepository<Topping,Integer> {
}
