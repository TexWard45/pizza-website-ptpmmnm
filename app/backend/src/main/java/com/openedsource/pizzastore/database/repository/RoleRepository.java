package com.openedsource.pizzastore.database.repository;

import com.openedsource.pizzastore.database.entity.ERole;
import com.openedsource.pizzastore.database.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
