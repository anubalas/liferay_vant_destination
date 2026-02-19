package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.AlertRule;
import com.example.iotmonitoring.model.DeviceData;
import com.example.iotmonitoring.repository.AlertRuleRepository;
import com.example.iotmonitoring.repository.DeviceDataRepository;
import com.example.iotmonitoring.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing alert rules and generating alerts based on device data.
 */
@Service
public class AlertService {

    @Autowired
    private AlertRuleRepository alertRuleRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceDataRepository deviceDataRepository;

    @Autowired
    private NotificationService notificationService;

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Defines a new alert rule for a specific device.
     * 
     * @param deviceId the ID of the device
     * @param threshold the threshold value for the alert
     * @param condition the condition for the alert (e.g., "greater than", "less than")
     * @param notificationMethod the method of notification (e.g., "email", "webhook")
     * @throws IllegalArgumentException if the input parameters are invalid
     */
    public void defineAlertRule(UUID deviceId, float threshold, String condition, String notificationMethod) {
        // Validate input parameters
        if (threshold < 0) {
            throw new IllegalArgumentException("Threshold must be a positive value.");
        }
        if (!deviceRepository.existsById(deviceId)) {
            throw new IllegalArgumentException("Device ID does not exist.");
        }
        // Create and save the alert rule
        AlertRule alertRule = new AlertRule(deviceId, threshold, condition, notificationMethod);
        alertRuleRepository.save(alertRule);
    }

    /**
     * Periodically checks device data against alert rules and sends notifications if conditions are met.
     */
    @Scheduled(fixedRate = 60000) // Check every minute
    public void generateAlerts() {
        List<AlertRule> alertRules = alertRuleRepository.findAll();
        for (AlertRule rule : alertRules) {
            Optional<DeviceData> deviceData = deviceDataRepository.findLatestDataByDeviceId(rule.getDeviceId());
            if (deviceData.isPresent()) {
                float value = deviceData.get().getData();
                if (checkCondition(value, rule.getThreshold(), rule.getCondition())) {
                    sendNotification(rule);
                }
            }
        }
    }

    private boolean checkCondition(float value, float threshold, String condition) {
        switch (condition) {
            case "greater than":
                return value > threshold;
            case "less than":
                return value < threshold;
            default:
                return false;
        }
    }

    private void sendNotification(AlertRule rule) {
        // Implement notification logic based on rule.getNotificationMethod()
        // This could involve sending an email, a webhook call, etc.
    }
}