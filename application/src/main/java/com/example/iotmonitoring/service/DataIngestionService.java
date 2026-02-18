package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.DeviceData;
import com.example.iotmonitoring.model.Device;
import com.example.iotmonitoring.repository.DeviceDataRepository;
import com.example.iotmonitoring.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Service for ingesting data from IoT devices and evaluating alert rules.
 */
@Service
public class DataIngestionService {

    @Autowired
    private DeviceDataRepository deviceDataRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    private final ExecutorService executorService;

    @Autowired
    public DataIngestionService(DeviceDataRepository deviceDataRepository, DeviceRepository deviceRepository) {
        this.deviceDataRepository = deviceDataRepository;
        this.deviceRepository = deviceRepository;
        this.executorService = Executors.newFixedThreadPool(10); // Thread pool for async processing
    }

    /**
     * Ingests incoming device data and evaluates alert rules.
     * 
     * @param deviceData Incoming device data.
     * @return true if data is valid and processed, false otherwise.
     */
    public boolean ingestData(DeviceData deviceData) {
        // Validate device ID
        Optional<Device> device = deviceRepository.findById(deviceData.getDeviceId());
        if (!device.isPresent()) {
            // Log error: Device ID not found
            return false;
        }

        // Validate timestamp
        if (deviceData.getTimestamp() == null || !isValidTimestamp(deviceData.getTimestamp().toString())) {
            // Log error: Invalid timestamp
            return false;
        }

        // Save to repository asynchronously
        executorService.submit(() -> deviceDataRepository.save(deviceData));

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
}