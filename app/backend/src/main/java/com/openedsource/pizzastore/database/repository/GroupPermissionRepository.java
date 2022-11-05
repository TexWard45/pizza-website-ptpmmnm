package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.GroupPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPermissionRepository extends JpaRepository<GroupPermissionEntity,Integer> {
}
