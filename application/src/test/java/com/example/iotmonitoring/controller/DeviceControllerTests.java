package com.example.iotmonitoring.controller;

import com.example.iotmonitoring.model.Device;
import com.example.iotmonitoring.service.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the DeviceController class.
 */
public class DeviceControllerTests {

    @Mock
    private DeviceService deviceService;

    @InjectMocks
    private DeviceController deviceController;

    private Device device;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        device = new Device();
        device.setId(UUID.randomUUID());
        device.setName("Test Device");
        device.setType("Sensor");
        device.setLocation("Room 1");
    }

    /**
     * Test for registering a device.
     */
    @Test
    public void testRegisterDevice() {
        when(deviceService.saveDevice(device)).thenReturn(device);
        ResponseEntity<Device> response = deviceController.registerDevice(device);
        assertEquals(device, response.getBody());
        verify(deviceService, times(1)).saveDevice(device);
    }
}