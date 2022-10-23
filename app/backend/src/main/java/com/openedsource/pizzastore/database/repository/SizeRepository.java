package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size,Integer> {
}
