package com.example.iotmonitoring.service;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.iotmonitoring.IotMonitoringApplication;
import com.example.iotmonitoring.controller.*;
import com.example.iotmonitoring.model.*;
import com.example.iotmonitoring.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = IotMonitoringApplication.class)
@AutoConfigureMockMvc
@Transactional
public class FunctionalTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Setup code if needed
    }

    @Test
    public void testDataIngestion() throws Exception {
        // Validate data ingestion via HTTP
        String jsonData = "{\"deviceId\":\"12345\", \"temperature\": 75.0}";
        mockMvc.perform(post("/api/data/ingest")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeviceRegistration() throws Exception {
        // Test device registration
        String deviceJson = "{\"name\":\"Device1\", \"type\":\"Sensor\", \"location\":\"Room1\"}";
        mockMvc.perform(post("/api/devices/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(deviceJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAlertTriggering() throws Exception {
        // Test alert triggering based on thresholds
        String alertJson = "{\"deviceId\":\"12345\", \"threshold\": 100.0, \"condition\":\"greater\"}";
        mockMvc.perform(post("/api/alerts/define")
                .contentType(MediaType.APPLICATION_JSON)
                .content(alertJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testHealthMonitoring() throws Exception {
        // Test health monitoring API
        mockMvc.perform(get("/api/devices/health")
                .param("deviceId", "12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("active"));
    }
}