package com.example.iotmonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.iotmonitoring.model.Role;

/**
 * RoleRepository provides CRUD operations for Role entity.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}