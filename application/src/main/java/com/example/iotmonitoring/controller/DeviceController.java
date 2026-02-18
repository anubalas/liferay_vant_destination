package com.example.iotmonitoring.controller;

import com.example.iotmonitoring.model.Device;
import com.example.iotmonitoring.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * DeviceController handles RESTful APIs for device management.
 */
@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    /**
     * Register a new device.
     * @param device Device object containing device details.
     * @return ResponseEntity with created device details.
     */
    @PostMapping
    public ResponseEntity<Device> registerDevice(@RequestBody Device device) {
        Device createdDevice = deviceService.registerDevice(device);
        return new ResponseEntity<>(createdDevice, HttpStatus.CREATED);
    }

    /**
     * Update an existing device.
     * @param id Device ID.
     * @param device Device object containing updated details.
     * @return ResponseEntity with updated device details.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable String id, @RequestBody Device device) {
        Device updatedDevice = deviceService.updateDevice(id, device);
        return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
    }

    /**
     * Delete a device by ID.
     * @param id Device ID.
     * @return ResponseEntity with status of deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable String id) {
        deviceService.deleteDevice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Fetch device details by ID.
     * @param id Device ID.
     * @return ResponseEntity with device details.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceDetails(@PathVariable String id) {
        Device device = deviceService.getDeviceDetails(id);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }

    /**
     * Fetch all devices.
     * @return ResponseEntity with list of devices.
     */
    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }
}