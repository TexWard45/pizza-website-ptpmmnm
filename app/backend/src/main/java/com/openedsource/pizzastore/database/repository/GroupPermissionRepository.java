package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.GroupPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupPermissionRepository extends JpaRepository<GroupPermission,Integer> {
}
