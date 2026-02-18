package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.AlertRule;
import com.example.iotmonitoring.model.Device;
import com.example.iotmonitoring.repository.AlertRuleRepository;
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
 * Integration tests for AlertRuleService to validate alert rule operations and API workflows.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AlertRuleServiceIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private AlertRuleRepository alertRuleRepository;

    private Device testDevice;
    private AlertRule testAlertRule;

    @BeforeEach
    public void setUp() {
        testDevice = new Device();
        testDevice.setName("Test Device");
        testDevice.setType("Sensor");
        testDevice.setLocation("Location A");
        deviceRepository.save(testDevice);

        testAlertRule = new AlertRule();
        testAlertRule.setDeviceId(testDevice.getId());
        testAlertRule.setThreshold(100.0);
        testAlertRule.setCondition("greater_than");
        testAlertRule.setNotificationMethod("email");
        alertRuleRepository.save(testAlertRule);
    }

    @Test
    public void testCreateAlertRule() throws Exception {
        mockMvc.perform(post("/api/alert-rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"deviceId\":\"" + testDevice.getId() + "\", \"threshold\":150.0, \"condition\":\"greater_than\", \"notificationMethod\":\"sms\"}")
        )
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andDo(print());
    }

    @Test
    public void testGetAlertRule() throws Exception {
        mockMvc.perform(get("/api/alert-rules/{id}", testAlertRule.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.threshold").value(testAlertRule.getThreshold()))
                .andDo(print());
    }

    @Test
    public void testUpdateAlertRule() throws Exception {
        mockMvc.perform(put("/api/alert-rules/{id}", testAlertRule.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"threshold\":200.0, \"condition\":\"less_than\", \"notificationMethod\":\"email\"}")
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(print());
    }

    @Test
    public void testDeleteAlertRule() throws Exception {
        mockMvc.perform(delete("/api/alert-rules/{id}", testAlertRule.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(print());
    }
}