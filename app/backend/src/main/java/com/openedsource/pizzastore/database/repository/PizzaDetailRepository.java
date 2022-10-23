package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.PizzaDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaDetailRepository extends JpaRepository<PizzaDetail,Integer> {
}
