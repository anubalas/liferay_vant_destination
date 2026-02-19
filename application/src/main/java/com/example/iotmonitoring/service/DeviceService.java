package com.example.iotmonitoring.service;

import org.springframework.stereotype.Service;
import com.example.iotmonitoring.model.Device;
import com.example.iotmonitoring.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    // Store device metadata
    public Device storeDeviceMetadata(Device device) {
        return deviceRepository.save(device);
    }

    // Update device metadata
    public Device updateDeviceMetadata(Device device) {
        return deviceRepository.save(device);
    }

    // Delete a device
    public void deleteDevice(UUID id) {
        deviceRepository.deleteById(id);
    }

    // Check device health
    public DeviceHealthStatus checkDeviceHealth(UUID id) {
        // Logic to check health
    }

    // Get health status for all devices
    public List<DeviceHealthStatus> getAllDeviceHealth() {
        // Logic to get health status
    }
}