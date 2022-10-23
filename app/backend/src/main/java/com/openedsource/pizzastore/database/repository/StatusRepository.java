package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Integer> {
}
