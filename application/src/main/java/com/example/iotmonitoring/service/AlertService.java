package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.AlertRule;
import com.example.iotmonitoring.repository.AlertRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing alert rules.
 */
@Service
public class AlertService {
    @Autowired
    private AlertRuleRepository alertRuleRepository;

    public AlertRule createAlertRule(AlertRule alertRule) {
        return alertRuleRepository.save(alertRule);
    }

    public Optional<AlertRule> updateAlertRule(AlertRule alertRule) {
        return Optional.of(alertRuleRepository.save(alertRule));
    }

    public void deleteAlertRule(UUID id) {
        alertRuleRepository.deleteById(id);
    }

    public List<AlertRule> getAlertRulesByDeviceId(UUID deviceId) {
        return alertRuleRepository.findByDeviceId(deviceId);
    }
}
