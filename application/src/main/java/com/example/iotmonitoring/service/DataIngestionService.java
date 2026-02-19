package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.DeviceData;
import com.example.iotmonitoring.repository.DeviceDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Service for handling data ingestion logic from IoT devices.
 */
@Service
public class DataIngestionService {

    @Autowired
    private DeviceDataRepository deviceDataRepository;

    /**
     * Method to process and store incoming device data in batches.
     *
     * @param deviceDataList The list of data received from devices.
     */
    public void processData(List<DeviceData> deviceDataList) {
        // Batch processing to reduce overhead of individual transactions
        List<DeviceData> batch = new ArrayList<>();
        for (DeviceData deviceData : deviceDataList) {
            batch.add(deviceData);
            if (batch.size() >= 100) { // Process in batches of 100
                deviceDataRepository.saveAll(batch);
                batch.clear();
            }
        }
        // Save any remaining data
        if (!batch.isEmpty()) {
            deviceDataRepository.saveAll(batch);
        }
    }
}