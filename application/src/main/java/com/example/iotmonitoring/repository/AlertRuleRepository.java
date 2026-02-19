package com.example.iotmonitoring.repository;

import com.example.iotmonitoring.model.AlertRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for AlertRule entity.
 */
@Repository
public interface AlertRuleRepository extends JpaRepository<AlertRule, UUID> {
    List<AlertRule> findByDeviceId(UUID deviceId);
}
