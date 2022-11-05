package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
}