package com.example.iotmonitoring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertGenerationService {

    @Autowired
    private NotificationService notificationService;

    public void generateAlert(String message) {
        // Logic to generate alert
        notificationService.logUnauthorizedAccess(message);
    }
}