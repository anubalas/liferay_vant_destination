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
import com.example.iotmonitoring.service.DataQueryService;
import com.example.iotmonitoring.model.DeviceData;

/**
 * Unit tests for DataQueryController.
 */
@WebMvcTest(DataQueryController.class)
public class DataQueryControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private DataQueryController dataQueryController;

    @Mock
    private DataQueryService dataQueryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetData_Success() throws Exception {
        mockMvc.perform(get(\"/api/data\")
                .param(\"deviceId\", \"valid-device-id\")
                .param(\"startTime\", \"2023-10-01T00:00:00Z\")
                .param(\"endTime\", \"2023-10-01T23:59:59Z\"))
                .andExpect(status().isOk());
    }
}