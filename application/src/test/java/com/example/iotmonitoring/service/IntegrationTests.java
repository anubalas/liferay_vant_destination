package com.example.iotmonitoring.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import com.example.iotmonitoring.IotMonitoringApplication;
import com.example.iotmonitoring.model.Device;
import com.example.iotmonitoring.model.DeviceData;
import com.example.iotmonitoring.repository.DeviceRepository;
import com.example.iotmonitoring.repository.DeviceDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest(classes = IotMonitoringApplication.class)
@AutoConfigureMockMvc
@Transactional
public class IntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceDataRepository deviceDataRepository;

    private Device testDevice;

    @BeforeEach
    public void setUp() {
        // Set up a test device
        testDevice = new Device();
        testDevice.setId(UUID.randomUUID());
        testDevice.setName("Test Device");
        testDevice.setType("Sensor");
        testDevice.setLocation("Test Location");
        deviceRepository.save(testDevice);
    }

    @Test
    public void testIngestData() throws Exception {
        // Ingest data via HTTP
        String jsonData = "{\"data\": {\"temperature\": 101}}";
        mockMvc.perform(post("/api/devices/" + testDevice.getId() + "/data")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData))
                .andExpect(status().isOk());

        // Verify that the data is stored
        DeviceData storedData = deviceDataRepository.findAll().get(0);
        assertEquals(101, storedData.getData().get("temperature"));
    }

    @Test
    public void testDeviceManagement() throws Exception {
        // Test registering a new device
        String newDeviceJson = "{\"name\": \"New Device\", \"type\": \"Sensor\", \"location\": \"New Location\"}";
        mockMvc.perform(post("/api/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newDeviceJson))
                .andExpect(status().isCreated());

        // Verify that the device is created
        assertEquals(2, deviceRepository.count());
    }

    @Test
    public void testFetchDeviceDetails() throws Exception {
        // Fetch device details
        mockMvc.perform(get("/api/devices/" + testDevice.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(testDevice.getName()));
    }

    @Test
    public void testAlertGeneration() throws Exception {
        // Ingest data that exceeds the threshold
        String jsonData = "{\"data\": {\"temperature\": 105}}";
        mockMvc.perform(post("/api/devices/" + testDevice.getId() + "/data")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData))
                .andExpect(status().isOk());

        // Verify that an alert is generated (this would depend on your alerting mechanism)
        // This is a placeholder for the actual alert verification logic
    }
}