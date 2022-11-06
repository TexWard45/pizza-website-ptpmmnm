package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.PizzaDetailEntity;
import com.openedsource.pizzastore.database.repository.PizzaDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PizzaDetailService {

    @Autowired
    private PizzaDetailRepository pizzaDetailRepository;

    public void insertPizzaDetail(PizzaDetailEntity baseEntity) {

        Optional<PizzaDetailEntity> base = pizzaDetailRepository.findById(baseEntity.getId());
        if (base.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        pizzaDetailRepository.save(baseEntity);
    }

    public void updatePizzaDetail(PizzaDetailEntity baseEntity) {
        pizzaDetailRepository.save(baseEntity);
    }
}
