package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.WarehouseReceiptEntity;
import com.openedsource.pizzastore.database.repository.WarehouseReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class WarehouseReceiptService {

    @Autowired
    private WarehouseReceiptRepository warehouseReceiptRepository;

    public void insertWarehouseReceipt(WarehouseReceiptEntity warehouseReceiptEntity) {

        Optional<WarehouseReceiptEntity> warehouseReceipt = warehouseReceiptRepository.findById(warehouseReceiptEntity.getId());
        if (warehouseReceipt.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        LocalDate localDate = LocalDate.now();
        warehouseReceiptEntity.setTime_created(localDate);
        warehouseReceiptRepository.save(warehouseReceiptEntity);
    }

    public void updateWarehouseReceipt(WarehouseReceiptEntity warehouseReceiptEntity) {
        LocalDate localDate = LocalDate.now();
        warehouseReceiptEntity.setTime_created(localDate);
        warehouseReceiptRepository.save(warehouseReceiptEntity);
    }
}
