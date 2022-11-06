package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.ToppingDetailEntity;
import com.openedsource.pizzastore.database.repository.ToppingDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ToppingDetailService {
    @Autowired
    private ToppingDetailRepository toppingDetailRepository;
    public void insertToppingDetail(ToppingDetailEntity toppingDetailEntity) {

        Optional<ToppingDetailEntity> toppingDetail = toppingDetailRepository.findById(toppingDetailEntity.getPizzaid());
        if (toppingDetail.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        toppingDetailRepository.save(toppingDetailEntity);
    }

    public void updateToppingDetail(ToppingDetailEntity toppingDetailEntity) {
        toppingDetailRepository.save(toppingDetailEntity);
    }
}
