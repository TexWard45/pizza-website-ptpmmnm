package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
}
