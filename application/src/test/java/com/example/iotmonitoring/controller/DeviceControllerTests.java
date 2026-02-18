package com.example.iotmonitoring.controller;

import com.example.iotmonitoring.model.Device;
import com.example.iotmonitoring.service.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * DeviceControllerTests contains unit tests for DeviceController.
 */
public class DeviceControllerTests {

    @InjectMocks
    private DeviceController deviceController;

    @Mock
    private DeviceService deviceService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();
    }

    @Test
    public void testRegisterDevice() {
        Device device = new Device();
        device.setName("Device1");

        when(deviceService.registerDevice(any(Device.class))).thenReturn(device);

        ResponseEntity<Device> response = deviceController.registerDevice(device);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdateDevice() {
        Device device = new Device();
        device.setName("UpdatedDevice");

        when(deviceService.updateDevice(any(String.class), any(Device.class))).thenReturn(device);

        ResponseEntity<Device> response = deviceController.updateDevice("1", device);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testDeleteDevice() {
        doNothing().when(deviceService).deleteDevice(any(String.class));

        ResponseEntity<Void> response = deviceController.deleteDevice("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetDeviceDetails() {
        Device device = new Device();
        device.setName("Device1");

        when(deviceService.getDeviceDetails(any(String.class))).thenReturn(device);

        ResponseEntity<Device> response = deviceController.getDeviceDetails("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
