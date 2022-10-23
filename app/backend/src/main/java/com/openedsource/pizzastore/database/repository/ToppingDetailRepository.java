package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.ToppingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToppingDetailRepository extends JpaRepository<ToppingDetail,Integer> {
}
