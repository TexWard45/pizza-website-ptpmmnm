package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.WarehouseReceiptDetailEntity;
import com.openedsource.pizzastore.database.repository.WarehouseReceiptDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseReceiptDetailService {
    @Autowired
    private WarehouseReceiptDetailRepository warehouseReceiptDetailRepository;

    public void insertWarehouseReceiptDetail(WarehouseReceiptDetailEntity warehouseReceiptDetailEntity) {

        Optional<WarehouseReceiptDetailEntity> warehouseReceiptDetail = warehouseReceiptDetailRepository.findById(warehouseReceiptDetailEntity.getId());
        if (warehouseReceiptDetail.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        warehouseReceiptDetailRepository.save(warehouseReceiptDetailEntity);
    }

    public void updateWarehouseReceiptDetail(WarehouseReceiptDetailEntity warehouseReceiptDetailEntity) {
        warehouseReceiptDetailRepository.save(warehouseReceiptDetailEntity);
    }
}
