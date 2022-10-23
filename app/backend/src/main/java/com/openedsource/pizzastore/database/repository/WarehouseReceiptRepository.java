package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.WarehouseReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseReceiptRepository extends JpaRepository<WarehouseReceipt,Integer> {
}
