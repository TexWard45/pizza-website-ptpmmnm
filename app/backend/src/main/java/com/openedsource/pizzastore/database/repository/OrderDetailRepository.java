package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
}
