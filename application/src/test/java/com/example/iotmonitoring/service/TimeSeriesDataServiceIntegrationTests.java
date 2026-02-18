package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.Device;
import com.example.iotmonitoring.model.TimeSeriesData;
import com.example.iotmonitoring.repository.DeviceRepository;
import com.example.iotmonitoring.repository.TimeSeriesDataRepository;
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
 * Integration tests for TimeSeriesDataService to validate time-series data operations and API workflows.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TimeSeriesDataServiceIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private TimeSeriesDataRepository timeSeriesDataRepository;

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
    public void testCreateTimeSeriesData() throws Exception {
        mockMvc.perform(post("/api/time-series-data")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"deviceId\":\"" + testDevice.getId() + "\", \"timestamp\":\"2023-01-01T00:00:00Z\", \"data\":{\"value\":42.0}}")
        )
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andDo(print());
    }

    @Test
    public void testGetTimeSeriesData() throws Exception {
        TimeSeriesData timeSeriesData = new TimeSeriesData();
        timeSeriesData.setDeviceId(testDevice.getId());
        timeSeriesData.setTimestamp(java.time.Instant.now());
        timeSeriesData.setData("{\"value\":42.0}");
        timeSeriesDataRepository.save(timeSeriesData);

        mockMvc.perform(get("/api/time-series-data/{deviceId}", testDevice.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.value").value(42.0))
                .andDo(print());
    }
}