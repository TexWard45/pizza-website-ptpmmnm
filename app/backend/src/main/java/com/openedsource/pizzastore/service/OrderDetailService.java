package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.OrderDetailEntity;
import com.openedsource.pizzastore.database.repository.OrderDetailRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailService {

    private OrderDetailRepository orderDetailRepository;

    public void insertOrderDetail(OrderDetailEntity orderDetailEntity) {

        Optional<OrderDetailEntity> orderDetail = orderDetailRepository.findById(orderDetailEntity.getId());
        if (orderDetail.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        orderDetailRepository.save(orderDetailEntity);
    }

    public void updateOrderDetail(OrderDetailEntity orderDetailEntity) {
        orderDetailRepository.save(orderDetailEntity);
    }
}
