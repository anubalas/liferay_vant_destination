package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.DeviceData;
import com.example.iotmonitoring.model.Device;
import com.example.iotmonitoring.repository.DeviceDataRepository;
import com.example.iotmonitoring.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

/**
 * Service for processing incoming data from IoT devices.
 */
@Service
public class DataProcessingService {

    @Autowired
    private DeviceDataRepository deviceDataRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    /**
     * Validates and formats incoming device data before storage.
     * 
     * @param deviceData Incoming device data.
     * @return true if data is valid and formatted, false otherwise.
     */
    public boolean processIncomingData(DeviceData deviceData) {
        // Validate device ID
        Optional<Device> device = deviceRepository.findById(deviceData.getDevice().getId());
        if (!device.isPresent()) {
            // Log error: Device ID not found
            return false;
        }

        // Validate timestamp
        if (deviceData.getTimestamp() == null || !isValidTimestamp(deviceData.getTimestamp().toString())) {
            // Log error: Invalid timestamp
            return false;
        }

        // Format data payload
        formatDataPayload(deviceData);

        // Save to repository
        deviceDataRepository.save(deviceData);
        return true;
    }

    /**
     * Validates the timestamp format.
     * 
     * @param timestamp Timestamp to validate.
     * @return true if valid, false otherwise.
     */
    private boolean isValidTimestamp(String timestamp) {
        try {
            Instant.parse(timestamp);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Formats the data payload into a suitable structure for storage.
     * 
     * @param deviceData Device data to format.
     */
    private void formatDataPayload(DeviceData deviceData) {
        // Example formatting logic (e.g., converting types, structuring JSONB)
        // deviceData.setData(...);
    }
}