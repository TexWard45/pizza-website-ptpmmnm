package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.ToppingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends JpaRepository<ToppingEntity,Integer> {
}
