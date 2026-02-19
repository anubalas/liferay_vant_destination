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

/**
 * Unit tests for HealthMonitoringController.
 */
@WebMvcTest(HealthMonitoringController.class)
class HealthMonitoringControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DeviceService deviceService;

    @InjectMocks
    private HealthMonitoringController healthMonitoringController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckDeviceHealth() throws Exception {
        String deviceId = "some-device-id";
        when(deviceService.checkDeviceHealth(deviceId)).thenReturn(new DeviceHealthStatus());

        mockMvc.perform(get("/devices/{id}/health", deviceId))
                .andExpect(status().isOk());
    }
}