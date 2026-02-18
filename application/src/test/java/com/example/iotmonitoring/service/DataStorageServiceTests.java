package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.DeviceMetadata;
import com.example.iotmonitoring.model.TimeSeriesData;
import com.example.iotmonitoring.repository.DeviceMetadataRepository;
import com.example.iotmonitoring.repository.TimeSeriesDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the DataStorageService class.
 */
public class DataStorageServiceTests {

    @InjectMocks
    private DataStorageService dataStorageService;

    @Mock
    private DeviceMetadataRepository deviceMetadataRepository;

    @Mock
    private TimeSeriesDataRepository timeSeriesDataRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDeviceMetadata() {
        DeviceMetadata deviceMetadata = new DeviceMetadata();
        deviceMetadata.setId(UUID.randomUUID());
        deviceMetadata.setName(\"Device1\");
        deviceMetadata.setType(\"Sensor\");
        deviceMetadata.setLocation(\"Location1\");

        when(deviceMetadataRepository.save(deviceMetadata)).thenReturn(deviceMetadata);

        DeviceMetadata savedDevice = dataStorageService.saveDeviceMetadata(deviceMetadata);

        assertNotNull(savedDevice);
        assertEquals(deviceMetadata.getName(), savedDevice.getName());
    }

    @Test
    public void testSaveTimeSeriesData() {
        TimeSeriesData timeSeriesData = new TimeSeriesData();
        timeSeriesData.setId(UUID.randomUUID());
        timeSeriesData.setDeviceId(UUID.randomUUID());
        timeSeriesData.setTimestamp(new Timestamp(System.currentTimeMillis()));
        timeSeriesData.setData(\"{\\\"temperature\\\": 25}\");

        when(timeSeriesDataRepository.save(timeSeriesData)).thenReturn(timeSeriesData);

        TimeSeriesData savedData = dataStorageService.saveTimeSeriesData(timeSeriesData);

        assertNotNull(savedData);
        assertEquals(timeSeriesData.getData(), savedData.getData());
    }
}
