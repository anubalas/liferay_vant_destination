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
import com.example.iotmonitoring.service.HealthMonitoringService;

/**
 * Unit tests for HealthMonitoringController.
 */
@WebMvcTest(HealthMonitoringController.class)
public class HealthMonitoringControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private HealthMonitoringController healthMonitoringController;

    @Mock
    private HealthMonitoringService healthMonitoringService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetHealthStatus() throws Exception {
        mockMvc.perform(get(\"/api/health\"))
                .andExpect(status().isOk())
                .andExpect(content().string(\"Service is up and running\"));
    }
}
