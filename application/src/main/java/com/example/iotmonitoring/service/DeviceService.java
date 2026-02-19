package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.Device;
import com.example.iotmonitoring.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing devices.
 */
@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    public Device createDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Optional<Device> getDeviceById(UUID id) {
        return deviceRepository.findById(id);
    }

    public Device updateDevice(Device device) {
        return deviceRepository.save(device);
    }

    public void deleteDevice(UUID id) {
        deviceRepository.deleteById(id);
    }
}
