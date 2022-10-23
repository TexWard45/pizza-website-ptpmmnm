package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Integer> {
}
