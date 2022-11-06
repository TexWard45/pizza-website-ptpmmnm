package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.WarehouseReceiptDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseReceiptDetailRepository extends JpaRepository<WarehouseReceiptDetailEntity,Integer> {
}
