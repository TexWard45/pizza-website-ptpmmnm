package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity,Integer> {
}
