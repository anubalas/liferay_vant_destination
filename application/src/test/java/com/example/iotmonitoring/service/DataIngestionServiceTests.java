package com.example.iotmonitoring.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.iotmonitoring.model.DeviceData;
import com.example.iotmonitoring.repository.DeviceDataRepository;

/**
 * Unit tests for DataIngestionService.
 */
public class DataIngestionServiceTests {
    @InjectMocks
    private DataIngestionService dataIngestionService;

    @Mock
    private DeviceDataRepository deviceDataRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIngestData() {
        DeviceData data = new DeviceData();
        data.setDeviceId(UUID.randomUUID());
        data.setTimestamp(System.currentTimeMillis());
        data.setData(\"{\\\"temperature\\\": 22}\");

        dataIngestionService.ingestData(data);

        verify(deviceDataRepository, times(1)).save(data);
    }
}
