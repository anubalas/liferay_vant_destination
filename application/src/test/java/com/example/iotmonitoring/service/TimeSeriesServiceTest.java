package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.DeviceData;
import com.example.iotmonitoring.repository.TimeSeriesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class TimeSeriesServiceTest {
    @Mock
    private TimeSeriesRepository timeSeriesRepository;

    @InjectMocks
    private TimeSeriesService timeSeriesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testStoreTimeSeriesData() {
        DeviceData deviceData = new DeviceData();
        deviceData.setId(UUID.randomUUID());
        deviceData.setDevice_id(UUID.randomUUID());
        deviceData.setTimestamp(new Timestamp(System.currentTimeMillis()));
        deviceData.setData("{\"value\": 100}");

        when(timeSeriesRepository.save(deviceData)).thenReturn(deviceData);

        DeviceData savedData = timeSeriesService.storeTimeSeriesData(deviceData);
        assertNotNull(savedData);
    }
}