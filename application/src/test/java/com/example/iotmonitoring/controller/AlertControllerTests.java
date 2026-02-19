package com.example.iotmonitoring.controller;

import com.example.iotmonitoring.model.AlertRule;
import com.example.iotmonitoring.service.AlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the AlertController class.
 */
public class AlertControllerTests {

    @Mock
    private AlertService alertService;

    @InjectMocks
    private AlertController alertController;

    private AlertRule alertRule;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        alertRule = new AlertRule();
        alertRule.setId(UUID.randomUUID());
        alertRule.setDeviceId(UUID.randomUUID());
        alertRule.setThreshold(100.0f);
        alertRule.setCondition("greater than");
        alertRule.setNotificationMethod("email");
    }

    @Test
    public void testCreateOrUpdateAlertRule() {
        when(alertService.saveAlertRule(alertRule)).thenReturn(alertRule);
        ResponseEntity<AlertRule> response = alertController.createOrUpdateAlertRule(alertRule);
        assertEquals(alertRule, response.getBody());
        verify(alertService, times(1)).saveAlertRule(alertRule);
    }

    @Test
    public void testDeleteAlertRule() {
        UUID id = alertRule.getId();
        ResponseEntity<Void> response = alertController.deleteAlertRule(id);
        assertEquals(204, response.getStatusCodeValue());
        verify(alertService, times(1)).deleteAlertRule(id);
    }

    @Test
    public void testGetAllAlertRules() {
        when(alertService.getAllAlertRules()).thenReturn(Collections.singletonList(alertRule));
        ResponseEntity<List<AlertRule>> response = alertController.getAllAlertRules();
        assertEquals(1, response.getBody().size());
        verify(alertService, times(1)).getAllAlertRules();
    }
}