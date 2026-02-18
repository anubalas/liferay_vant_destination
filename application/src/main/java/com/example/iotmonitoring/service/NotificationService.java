package com.example.iotmonitoring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void logUnauthorizedAccess(String username) {
        logger.warn("Unauthorized access attempt by user: {}", username);
    }
}