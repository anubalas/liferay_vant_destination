package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.Device;
import com.example.iotmonitoring.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * DeviceService handles business logic for device management.
 */
@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    /**
     * Register a new device.
     * @param device Device object containing device details.
     * @return Created Device object.
     */
    public Device registerDevice(Device device) {
        device.setId(UUID.randomUUID());
        device.setCreatedAt(LocalDateTime.now());
        device.setUpdatedAt(LocalDateTime.now());
        return deviceRepository.save(device);
    }

    /**
     * Update an existing device.
     * @param id Device ID.
     * @param device Device object containing updated details.
     * @return Updated Device object.
     */
    public Device updateDevice(String id, Device device) {
        Optional<Device> existingDevice = deviceRepository.findById(UUID.fromString(id));
        if (existingDevice.isPresent()) {
            Device updatedDevice = existingDevice.get();
            updatedDevice.setName(device.getName());
            updatedDevice.setType(device.getType());
            updatedDevice.setLocation(device.getLocation());
            updatedDevice.setUpdatedAt(LocalDateTime.now());
            return deviceRepository.save(updatedDevice);
        }
        return null; // Handle not found case appropriately
    }

    /**
     * Delete a device by ID.
     * @param id Device ID.
     */
    public void deleteDevice(String id) {
        deviceRepository.deleteById(UUID.fromString(id));
    }

    /**
     * Fetch device details by ID.
     * @param id Device ID.
     * @return Device object.
     */
    public Device getDeviceDetails(String id) {
        return deviceRepository.findById(UUID.fromString(id)).orElse(null); // Handle not found case appropriately
    }

    /**
     * Fetch all devices.
     * @return List of Device objects.
     */
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
}