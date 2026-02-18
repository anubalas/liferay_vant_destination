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
 * Integration tests for DeviceService to validate database operations and API workflows.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class DeviceServiceIntegrationTests {

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
    public void testCreateDevice() throws Exception {
        mockMvc.perform(post("/api/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New Device\", \"type\":\"Sensor\", \"location\":\"Location B\"}")
        )
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andDo(print());
    }

    @Test
    public void testGetDevice() throws Exception {
        mockMvc.perform(get("/api/devices/{id}", testDevice.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testDevice.getName()))
                .andDo(print());
    }

    @Test
    public void testUpdateDevice() throws Exception {
        mockMvc.perform(put("/api/devices/{id}", testDevice.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Device\", \"type\":\"Sensor\", \"location\":\"Location C\"}")
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(print());
    }

    @Test
    public void testDeleteDevice() throws Exception {
        mockMvc.perform(delete("/api/devices/{id}", testDevice.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(print());
    }
}