package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.Base;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository extends JpaRepository<Base,Integer> {
}
