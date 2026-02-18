package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.DeviceData;
import com.example.iotmonitoring.repository.DeviceDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the DataQueryService class.
 */
public class DataQueryServiceTests {

    @Mock
    private DeviceDataRepository deviceDataRepository;

    @InjectMocks
    private DataQueryService dataQueryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetHistoricalData() {
        // Arrange
        DeviceData data1 = new DeviceData();
        data1.setDeviceId("device1");
        data1.setTimestamp(LocalDateTime.now().minusDays(1));
        data1.setData("{\"value\": 150}");

        DeviceData data2 = new DeviceData();
        data2.setDeviceId("device2");
        data2.setTimestamp(LocalDateTime.now().minusDays(2));
        data2.setData("{\"value\": 80}");

        when(deviceDataRepository.findHistoricalData(any(), any(), any(), any(), any())).thenReturn(Arrays.asList(data1, data2));

        // Act
        List<DeviceData> result = dataQueryService.getHistoricalData(Arrays.asList("device1", "device2"), LocalDateTime.now().minusDays(3), LocalDateTime.now(), null, null);

        // Assert
        assertEquals(2, result.size());
    }
}