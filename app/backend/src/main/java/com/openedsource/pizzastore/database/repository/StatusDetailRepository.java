package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.StatusDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusDetailRepository extends JpaRepository<StatusDetail,Integer> {
}
