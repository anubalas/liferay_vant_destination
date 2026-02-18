package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.DeviceData;
import com.example.iotmonitoring.repository.DeviceDataRepository;
import com.example.iotmonitoring.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for DataProcessingService.
 */
public class DataProcessingServiceTests {

    @InjectMocks
    private DataProcessingService dataProcessingService;

    @Mock
    private DeviceDataRepository deviceDataRepository;

    @Mock
    private DeviceRepository deviceRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessIncomingData_ValidData() {
        DeviceData deviceData = new DeviceData();
        deviceData.setDeviceId(\"valid-device-id\");
        deviceData.setTimestamp(\"2023-10-01T12:00:00Z\");

        when(deviceRepository.findById(deviceData.getDeviceId())).thenReturn(Optional.of(new Device()));

        boolean result = dataProcessingService.processIncomingData(deviceData);
        assertTrue(result);
        verify(deviceDataRepository, times(1)).save(deviceData);
    }

    @Test
    public void testProcessIncomingData_InvalidDeviceId() {
        DeviceData deviceData = new DeviceData();
        deviceData.setDeviceId(\"invalid-device-id\");
        deviceData.setTimestamp(\"2023-10-01T12:00:00Z\");

        when(deviceRepository.findById(deviceData.getDeviceId())).thenReturn(Optional.empty());

        boolean result = dataProcessingService.processIncomingData(deviceData);
        assertFalse(result);
        verify(deviceDataRepository, never()).save(deviceData);
    }

    @Test
    public void testProcessIncomingData_InvalidTimestamp() {
        DeviceData deviceData = new DeviceData();
        deviceData.setDeviceId(\"valid-device-id\");
        deviceData.setTimestamp(\"invalid-timestamp\");

        when(deviceRepository.findById(deviceData.getDeviceId())).thenReturn(Optional.of(new Device()));

        boolean result = dataProcessingService.processIncomingData(deviceData);
        assertFalse(result);
        verify(deviceDataRepository, never()).save(deviceData);
    }
}
