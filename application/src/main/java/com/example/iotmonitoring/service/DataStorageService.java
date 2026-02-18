package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.DeviceMetadata;
import com.example.iotmonitoring.model.TimeSeriesData;
import com.example.iotmonitoring.repository.DeviceMetadataRepository;
import com.example.iotmonitoring.repository.TimeSeriesDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Service for handling data storage operations for devices and time-series data.
 */
@Service
public class DataStorageService {

    @Autowired
    private DeviceMetadataRepository deviceMetadataRepository;

    @Autowired
    private TimeSeriesDataRepository timeSeriesDataRepository;

    /**
     * Save device metadata to the database.
     */
    public DeviceMetadata saveDeviceMetadata(DeviceMetadata deviceMetadata) {
        deviceMetadata.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        deviceMetadata.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return deviceMetadataRepository.save(deviceMetadata);
    }

    /**
     * Save time-series data to the database.
     */
    public TimeSeriesData saveTimeSeriesData(TimeSeriesData timeSeriesData) {
        timeSeriesData.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return timeSeriesDataRepository.save(timeSeriesData);
    }
}