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
import com.example.iotmonitoring.service.DataIngestionService;
import com.example.iotmonitoring.model.DeviceData;

/**
 * Unit tests for DataIngestionController.
 */
@WebMvcTest(DataIngestionController.class)
public class DataIngestionControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private DataIngestionController dataIngestionController;

    @Mock
    private DataIngestionService dataIngestionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIngestData() throws Exception {
        DeviceData data = new DeviceData();
        data.setDeviceId(UUID.randomUUID());
        data.setTimestamp(System.currentTimeMillis());
        data.setData(\"{\\\"temperature\\\": 22}\");

        mockMvc.perform(post(\"/api/data\")
                .contentType(\"application/json\")
                .content(\"{\\\"deviceId\\\": \\\"\" + data.getDeviceId() + \"\\\", \\\"timestamp\\\": \" + data.getTimestamp() + \", \\\"data\\\": \\\"{\\\\\\\"temperature\\\\\\\": 22}\\\"}\")
                )
                .andExpect(status().isOk())
                .andExpect(content().string(\"Data ingested successfully\"));
    }
}
