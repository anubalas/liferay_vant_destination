package com.example.iotmonitoring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataQueryService {

    @Autowired
    private NotificationService notificationService;

    public void queryData() {
        // Logic to query data
    }
}