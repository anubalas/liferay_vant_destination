package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.DeviceData;
import com.example.iotmonitoring.model.AlertRule;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.mockito.Mockito.*;

/**
 * Unit tests for AlertGenerationService.
 */
@SpringBootTest
public class AlertGenerationServiceTests {

    @InjectMocks
    private AlertGenerationService alertGenerationService;

    @Mock
    private AlertRuleEvaluationService alertRuleEvaluationService;

    @Mock
    private ThresholdEvaluationService thresholdEvaluationService;

    @Autowired
    public AlertGenerationServiceTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessDeviceData_TriggersAlert() {
        DeviceData deviceData = new DeviceData();
        deviceData.setDevice(new Device());
        deviceData.getDevice().setId("device-id");
        deviceData.setData(new Data());
        deviceData.getData().setValue(150.0f);

        AlertRule alertRule = new AlertRule();
        alertRule.setId("alert-rule-id");
        alertRule.setThreshold(100.0f);
        alertRule.setCondition("greater than");
        alertRule.setNotificationMethod("REST API");

        when(thresholdEvaluationService.getAlertRulesForDevice("device-id")).thenReturn(Collections.singletonList(alertRule));

        alertGenerationService.processDeviceData(deviceData);

        verify(alertRuleEvaluationService, times(1)).evaluateAlertRules(Collections.singletonList(alertRule), 150.0f);
    }
}