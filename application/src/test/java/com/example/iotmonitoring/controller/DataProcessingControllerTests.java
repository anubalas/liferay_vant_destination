package com.example.iotmonitoring.controller;

import com.example.iotmonitoring.model.DeviceData;
import com.example.iotmonitoring.service.DataProcessingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for DataProcessingController.
 */
public class DataProcessingControllerTests {

    @InjectMocks
    private DataProcessingController dataProcessingController;

    @Mock
    private DataProcessingService dataProcessingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIngestData_Success() {
        DeviceData deviceData = new DeviceData();
        deviceData.setDeviceId(\"valid-device-id\");
        deviceData.setTimestamp(\"2023-10-01T12:00:00Z\");

        when(dataProcessingService.processIncomingData(deviceData)).thenReturn(true);

        ResponseEntity<String> response = dataProcessingController.ingestData(deviceData);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(\"Data processed successfully\", response.getBody());
    }

    @Test
    public void testIngestData_InvalidData() {
        DeviceData deviceData = new DeviceData();
        deviceData.setDeviceId(\"invalid-device-id\");
        deviceData.setTimestamp(\"2023-10-01T12:00:00Z\");

        when(dataProcessingService.processIncomingData(deviceData)).thenReturn(false);

        ResponseEntity<String> response = dataProcessingController.ingestData(deviceData);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(\"Invalid data\", response.getBody());
    }
}