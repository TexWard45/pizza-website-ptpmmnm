package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.PizzaEntity;
import com.openedsource.pizzastore.database.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PizzaService {
    

    @Autowired
    private PizzaRepository pizzaRepository;
    public void insertPizza(PizzaEntity pizzaEntity) {

        Optional<PizzaEntity> pizza = pizzaRepository.findById(pizzaEntity.getId());
        if (pizza.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        pizzaRepository.save(pizzaEntity);
    }

    public void updatePizza(PizzaEntity pizzaEntity) {
        pizzaRepository.save(pizzaEntity);
    }
}
