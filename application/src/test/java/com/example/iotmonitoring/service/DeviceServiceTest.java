package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.Device;
import com.example.iotmonitoring.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class DeviceServiceTest {
    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testStoreDeviceMetadata() {
        Device device = new Device();
        device.setId(UUID.randomUUID());
        device.setName("Device1");
        device.setType("Sensor");
        device.setLocation("Location1");

        when(deviceRepository.save(device)).thenReturn(device);

        Device savedDevice = deviceService.storeDeviceMetadata(device);
        assertNotNull(savedDevice);
    }
}