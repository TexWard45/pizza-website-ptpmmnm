package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.OrderEntity;
import com.openedsource.pizzastore.database.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void insertOrder(OrderEntity orderEntity) {

        Optional<OrderEntity> order = orderRepository.findById(orderEntity.getId());
        if (order.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        LocalDate localDate = LocalDate.now();
        orderEntity.setOrdertime(localDate);
        orderRepository.save(orderEntity);

    }

    public void updateOrder(OrderEntity orderEntity) {
            LocalDate localDate = LocalDate.now();
            orderEntity.setOrdertime(localDate);
            orderRepository.save(orderEntity);

    }
}
