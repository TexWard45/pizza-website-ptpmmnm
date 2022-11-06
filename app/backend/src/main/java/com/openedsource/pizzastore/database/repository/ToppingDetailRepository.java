package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.ToppingDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingDetailRepository extends JpaRepository<ToppingDetailEntity,Integer> {
}
