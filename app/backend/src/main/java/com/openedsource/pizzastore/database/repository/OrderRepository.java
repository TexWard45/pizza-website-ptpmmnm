package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
