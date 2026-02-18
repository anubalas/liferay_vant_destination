package com.example.iotmonitoring.controller;

import com.example.iotmonitoring.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devices")
public class HealthMonitoringController {

    @Autowired
    private DeviceService deviceService;

    /**
     * Get the last communication time of a device.
     *
     * @param deviceId the ID of the device
     * @return ResponseEntity containing the last communication time or an error message
     */
    @GetMapping("/{deviceId}/last-communication")
    public ResponseEntity<?> getLastCommunicationTime(@PathVariable String deviceId) {
        return deviceService.getLastCommunicationTime(deviceId);
    }

    /**
     * Get the current status of a device.
     *
     * @param deviceId the ID of the device
     * @return ResponseEntity containing the current status of the device
     */
    @GetMapping("/{deviceId}/status")
    public ResponseEntity<?> getDeviceStatus(@PathVariable String deviceId) {
        return deviceService.getDeviceStatus(deviceId);
    }
}