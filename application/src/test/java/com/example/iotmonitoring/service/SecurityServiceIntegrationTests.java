package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.Device;
import com.example.iotmonitoring.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Integration tests for SecurityService to validate security operations and API workflows.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SecurityServiceIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DeviceRepository deviceRepository;

    private Device testDevice;

    @BeforeEach
    public void setUp() {
        testDevice = new Device();
        testDevice.setName("Test Device");
        testDevice.setType("Sensor");
        testDevice.setLocation("Location A");
        deviceRepository.save(testDevice);
    }

    @Test
    public void testUnauthorizedAccess() throws Exception {
        mockMvc.perform(get("/api/devices/{id}/health", testDevice.getId()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(print());
    }
}