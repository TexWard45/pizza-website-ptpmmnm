package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.BaseEntity;
import com.openedsource.pizzastore.database.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BaseService {
    @Autowired
    BaseRepository baseRepository;

    public void insertBase(BaseEntity baseEntity) {

        Optional<BaseEntity> base = baseRepository.findById(baseEntity.getId());
        if (base.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        baseRepository.save(baseEntity);
    }

    public void updateBase(BaseEntity baseEntity) {
        baseRepository.save(baseEntity);
    }
}
