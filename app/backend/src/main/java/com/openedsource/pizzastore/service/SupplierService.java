package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.SupplierEntity;
import com.openedsource.pizzastore.database.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public void insertSupplier(SupplierEntity supplierEntity) {

        Optional<SupplierEntity> base = supplierRepository.findById(supplierEntity.getId());
        if (base.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        supplierRepository.save(supplierEntity);
    }

    public void updateSupplier(SupplierEntity supplierEntity) {
        supplierRepository.save(supplierEntity);
    }
}
