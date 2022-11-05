package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository extends JpaRepository<BaseEntity,Integer> {
}
