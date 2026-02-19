package com.example.iotmonitoring.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import com.example.iotmonitoring.service.DeviceService;
import com.example.iotmonitoring.model.Device;
import java.util.UUID;

/**
 * Unit tests for DeviceController.
 */
@WebMvcTest(DeviceController.class)
class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DeviceService deviceService;

    @InjectMocks
    private DeviceController deviceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterDevice() throws Exception {
        Device device = new Device();
        device.setId(UUID.randomUUID());
        device.setName("Test Device");
        device.setType("Sensor");
        device.setLocation("Room 1");

        when(deviceService.storeDeviceMetadata(any(Device.class))).thenReturn(device);

        mockMvc.perform(post("/devices")
                .contentType("application/json")
                .content("{\"name\": \"Test Device\", \"type\": \"Sensor\", \"location\": \"Room 1\"}")
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value("Test Device"));
    }
}