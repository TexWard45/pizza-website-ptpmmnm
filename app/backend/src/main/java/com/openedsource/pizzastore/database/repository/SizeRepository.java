package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<SizeEntity,Integer> {
}
