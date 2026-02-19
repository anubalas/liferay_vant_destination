package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.DeviceData;
import com.example.iotmonitoring.repository.DeviceDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class for managing device data.
 */
@Service
public class DeviceDataService {
    @Autowired
    private DeviceDataRepository deviceDataRepository;

    /**
     * Add new device data to the repository.
     *
     * @param deviceData The data to be added.
     * @return The saved device data.
     */
    public DeviceData addDeviceData(DeviceData deviceData) {
        return deviceDataRepository.save(deviceData);
    }

    /**
     * Retrieve device data by device ID.
     *
     * @param deviceId The ID of the device.
     * @return List of device data associated with the device ID.
     */
    public List<DeviceData> getDeviceDataByDeviceId(UUID deviceId) {
        return deviceDataRepository.findByDeviceId(deviceId);
    }

    /**
     * Delete device data by ID.
     *
     * @param id The ID of the device data to be deleted.
     */
    public void deleteDeviceData(UUID id) {
        deviceDataRepository.deleteById(id);
    }
}
