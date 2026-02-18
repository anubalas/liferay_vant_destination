package com.example.iotmonitoring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AlertRuleService {

    @Autowired
    private NotificationService notificationService;

    @PreAuthorize("hasRole('ADMIN')")
    public void createAlertRule() {
        // Implementation for creating alert rule
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void viewAlertRules() {
        // Implementation for viewing alert rules
    }
}