package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.ToppingEntity;
import com.openedsource.pizzastore.database.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ToppingService {
    @Autowired
    private ToppingRepository toppingRepository;

    public void insertTopping(ToppingEntity toppingEntity) {

        Optional<ToppingEntity> topping = toppingRepository.findById(toppingEntity.getId());
        if (topping.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        toppingRepository.save(toppingEntity);
    }

    public void updateTopping(ToppingEntity toppingEntity) {
        toppingRepository.save(toppingEntity);
    }
}
