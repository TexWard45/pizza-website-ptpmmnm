package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}