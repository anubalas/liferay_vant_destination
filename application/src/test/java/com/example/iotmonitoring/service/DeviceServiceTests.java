package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.Device;
import com.example.iotmonitoring.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the DeviceService class.
 */
public class DeviceServiceTests {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

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
     * Test for saving a device.
     */
    @Test
    public void testSaveDevice() {
        when(deviceRepository.save(device)).thenReturn(device);
        Device savedDevice = deviceService.saveDevice(device);
        assertEquals(device.getId(), savedDevice.getId());
        verify(deviceRepository, times(1)).save(device);
    }

    /**
     * Test for retrieving a device by ID.
     */
    @Test
    public void testGetDeviceById() {
        when(deviceRepository.findById(device.getId())).thenReturn(java.util.Optional.of(device));
        Device foundDevice = deviceService.getDeviceById(device.getId());
        assertEquals(device.getId(), foundDevice.getId());
        verify(deviceRepository, times(1)).findById(device.getId());
    }
}