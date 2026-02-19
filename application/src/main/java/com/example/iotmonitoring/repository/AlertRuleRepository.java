package com.example.iotmonitoring.repository;

import com.example.iotmonitoring.model.AlertRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing alert rules in the database.
 */
@Repository
public interface AlertRuleRepository extends JpaRepository<AlertRule, UUID> {
}