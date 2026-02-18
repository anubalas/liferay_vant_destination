package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.Device;
import com.example.iotmonitoring.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * DeviceServiceTests contains unit tests for DeviceService.
 */
public class DeviceServiceTests {

    @InjectMocks
    private DeviceService deviceService;

    @Mock
    private DeviceRepository deviceRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterDevice() {
        Device device = new Device();
        device.setName("Device1");
        device.setType("Sensor");
        device.setLocation("Location1");

        when(deviceRepository.save(any(Device.class))).thenReturn(device);

        Device createdDevice = deviceService.registerDevice(device);

        assertNotNull(createdDevice);
        assertEquals("Device1", createdDevice.getName());
    }

    @Test
    public void testUpdateDevice() {
        Device existingDevice = new Device();
        existingDevice.setId(UUID.randomUUID());
        existingDevice.setName("Device1");
        existingDevice.setType("Sensor");
        existingDevice.setLocation("Location1");

        when(deviceRepository.findById(existingDevice.getId())).thenReturn(Optional.of(existingDevice));
        when(deviceRepository.save(any(Device.class))).thenReturn(existingDevice);

        Device updatedDevice = new Device();
        updatedDevice.setName("UpdatedDevice");
        updatedDevice.setType("Sensor");
        updatedDevice.setLocation("Location1");

        Device result = deviceService.updateDevice(existingDevice.getId().toString(), updatedDevice);

        assertNotNull(result);
        assertEquals("UpdatedDevice", result.getName());
    }

    @Test
    public void testDeleteDevice() {
        Device device = new Device();
        device.setId(UUID.randomUUID());

        doNothing().when(deviceRepository).deleteById(device.getId());

        deviceService.deleteDevice(device.getId().toString());

        verify(deviceRepository, times(1)).deleteById(device.getId());
    }

    @Test
    public void testGetDeviceDetails() {
        Device device = new Device();
        device.setId(UUID.randomUUID());
        device.setName("Device1");

        when(deviceRepository.findById(device.getId())).thenReturn(Optional.of(device));

        Device result = deviceService.getDeviceDetails(device.getId().toString());

        assertNotNull(result);
        assertEquals("Device1", result.getName());
    }

    @Test
    public void testGetAllDevices() {
        List<Device> devices = new ArrayList<>();
        devices.add(new Device());
        devices.add(new Device());

        when(deviceRepository.findAll()).thenReturn(devices);

        List<Device> result = deviceService.getAllDevices();

        assertEquals(2, result.size());
    }
}