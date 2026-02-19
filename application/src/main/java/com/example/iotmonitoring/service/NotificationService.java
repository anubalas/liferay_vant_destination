package com.example.iotmonitoring.service;

import org.springframework.stereotype.Service;

/**
 * Service class for handling notifications based on alert rules.
 */
@Service
public class NotificationService {

    public void sendEmail(String to, String subject, String body) {
        // Implement email sending logic here
    }

    public void sendWebhook(String url, String payload) {
        // Implement webhook sending logic here
    }
}