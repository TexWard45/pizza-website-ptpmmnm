package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.StatusDetailEntity;
import com.openedsource.pizzastore.database.repository.StatusDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusDetailService {

    @Autowired
    private StatusDetailRepository statusDetailRepository;

    public void insertStatusDetail(StatusDetailEntity statusDetailEntity) {

        Optional<StatusDetailEntity> statusDetail = statusDetailRepository.findById(statusDetailEntity.getOrderid());
        if (statusDetail.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        statusDetailRepository.save(statusDetailEntity);
    }

    public void updateStatusDetail(StatusDetailEntity statusDetailEntity) {
        statusDetailRepository.save(statusDetailEntity);
    }
}
