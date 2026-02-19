package com.example.iotmonitoring.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.iotmonitoring.service.DeviceService;

@RestController
@RequestMapping("/devices")
public class HealthMonitoringController {

    @Autowired
    private DeviceService deviceService;

    // Check the health of a specific device
    @GetMapping("/{id}/health")
    public ResponseEntity<DeviceHealthStatus> checkDeviceHealth(@PathVariable String id) {
        // Logic to check device health
        return ResponseEntity.ok(deviceService.checkDeviceHealth(id));
    }

    // Retrieve health status for all devices
    @GetMapping("/health")
    public ResponseEntity<List<DeviceHealthStatus>> getAllDeviceHealth() {
        // Logic to retrieve health status
        return ResponseEntity.ok(deviceService.getAllDeviceHealth());
    }
}