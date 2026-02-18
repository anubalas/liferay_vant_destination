package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.AlertRule;
import com.example.iotmonitoring.repository.AlertRuleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.mockito.Mockito.*;

/**
 * Unit tests for ThresholdEvaluationService.
 */
@SpringBootTest
public class ThresholdEvaluationServiceTests {

    @InjectMocks
    private ThresholdEvaluationService thresholdEvaluationService;

    @Mock
    private AlertRuleRepository alertRuleRepository;

    @Autowired
    public ThresholdEvaluationServiceTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAlertRulesForDevice() {
        String deviceId = "device-id";
        AlertRule alertRule = new AlertRule();
        alertRule.setId("alert-rule-id");

        when(alertRuleRepository.findByDeviceId(deviceId)).thenReturn(Collections.singletonList(alertRule));

        List<AlertRule> alertRules = thresholdEvaluationService.getAlertRulesForDevice(deviceId);

        assertEquals(1, alertRules.size());
        assertEquals("alert-rule-id", alertRules.get(0).getId());
    }
}