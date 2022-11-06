package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity,Integer> {
}
