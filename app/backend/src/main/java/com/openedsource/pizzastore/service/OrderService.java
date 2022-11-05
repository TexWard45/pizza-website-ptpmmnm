package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.OrderEntity;
import com.openedsource.pizzastore.database.repository.OrderRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    OrderRepository orderRepository;

    public void insertOrder(OrderEntity orderEntity) {

        Optional<OrderEntity> order = orderRepository.findById(orderEntity.getId());
        if (order.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        orderRepository.save(orderEntity);
    }

    public void updateOrder(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
    }
}
