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
import com.example.iotmonitoring.service.TimeSeriesService;
import java.util.List;

/**
 * Unit tests for DataQueryController.
 */
@WebMvcTest(DataQueryController.class)
class DataQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TimeSeriesService timeSeriesService;

    @InjectMocks
    private DataQueryController dataQueryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDeviceData() throws Exception {
        String deviceId = "some-device-id";
        when(timeSeriesService.getDeviceData(eq(deviceId), anyString(), anyString())).thenReturn(List.of());

        mockMvc.perform(get("/devices/{id}/data", deviceId))
                .andExpect(status().isOk());
    }
}