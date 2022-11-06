package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.SizeEntity;
import com.openedsource.pizzastore.database.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    public void insertSize(SizeEntity sizeEntity) {

        Optional<SizeEntity> size = sizeRepository.findById(sizeEntity.getId());
        if (size.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        sizeRepository.save(sizeEntity);
    }

    public void updateSize(SizeEntity sizeEntity) {
        sizeRepository.save(sizeEntity);
    }
}
