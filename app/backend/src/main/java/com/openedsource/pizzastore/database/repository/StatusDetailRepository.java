package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.StatusDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDetailRepository extends JpaRepository<StatusDetailEntity,Integer> {
}
