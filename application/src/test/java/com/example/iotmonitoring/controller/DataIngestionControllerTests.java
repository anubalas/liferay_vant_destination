package com.example.iotmonitoring.controller;

import com.example.iotmonitoring.controller.DataIngestionController.DeviceData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for DataIngestionController.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class DataIngestionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test for successful data ingestion.
     */
    @Test
    public void testIngestData_Success() throws Exception {
        DeviceData deviceData = new DeviceData();
        deviceData.setTimestamp("2023-10-01T12:00:00Z");
        deviceData.setValue(25.5);
        deviceData.setMetadata("Temperature sensor");

        mockMvc.perform(post("/api/devices/data")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{'timestamp':'" + deviceData.getTimestamp() + "', 'value':" + deviceData.getValue() + ", 'metadata':'" + deviceData.getMetadata() + "'}]\