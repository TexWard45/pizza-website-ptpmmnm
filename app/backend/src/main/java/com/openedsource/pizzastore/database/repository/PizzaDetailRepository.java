package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.PizzaDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaDetailRepository extends JpaRepository<PizzaDetailEntity,Integer> {
}
