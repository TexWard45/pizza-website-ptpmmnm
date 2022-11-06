package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.StatusEntity;
import com.openedsource.pizzastore.database.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    public void insertStatus(StatusEntity statusEntity) {

        Optional<StatusEntity> base = statusRepository.findById(statusEntity.getId());
        if (base.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        statusRepository.save(statusEntity);
    }

    public void updateStatus(StatusEntity statusEntity) {
        statusRepository.save(statusEntity);
    }
}
