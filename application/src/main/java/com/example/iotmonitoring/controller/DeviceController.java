package com.example.iotmonitoring.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.iotmonitoring.service.DeviceService;
import com.example.iotmonitoring.model.Device;
import java.util.UUID;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    // Register a new IoT device
    @PostMapping
    public ResponseEntity<Device> registerDevice(@RequestBody Device device) {
        device.setId(UUID.randomUUID()); // Generate UUID
        Device savedDevice = deviceService.storeDeviceMetadata(device);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDevice);
    }

    // Update an existing device's details
    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable UUID id, @RequestBody Device device) {
        device.setId(id); // Set the ID for the update
        Device updatedDevice = deviceService.updateDeviceMetadata(device);
        return ResponseEntity.ok(updatedDevice);
    }

    // Remove a device from the system
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable UUID id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}