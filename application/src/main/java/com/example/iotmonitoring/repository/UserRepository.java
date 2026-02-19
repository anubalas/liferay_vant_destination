package com.example.iotmonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.iotmonitoring.model.User;

/**
 * UserRepository provides CRUD operations for User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}