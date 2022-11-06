package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.WarehouseReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseReceiptRepository extends JpaRepository<WarehouseReceiptEntity,Integer> {
}
