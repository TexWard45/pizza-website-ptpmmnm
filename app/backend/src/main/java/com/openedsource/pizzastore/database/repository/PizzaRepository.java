package com.openedsource.pizzastore.database.repository;


import com.openedsource.pizzastore.database.entity.PizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<PizzaEntity,Integer> {
}
