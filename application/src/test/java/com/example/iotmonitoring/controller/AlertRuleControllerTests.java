package com.example.iotmonitoring.controller;

import com.example.iotmonitoring.model.AlertRule;
import com.example.iotmonitoring.service.AlertRuleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for AlertRuleController.
 */
public class AlertRuleControllerTests {

    @InjectMocks
    private AlertRuleController alertRuleController;

    @Mock
    private AlertRuleService alertRuleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAlertRule() {
        AlertRule alertRule = new AlertRule();
        alertRule.setId(UUID.randomUUID());
        when(alertRuleService.createAlertRule(any(AlertRule.class))).thenReturn(alertRule);

        ResponseEntity<AlertRule> response = alertRuleController.createAlertRule(alertRule);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(alertRule, response.getBody());
    }

    @Test
    public void testGetAlertRule() {
        UUID id = UUID.randomUUID();
        AlertRule alertRule = new AlertRule();
        alertRule.setId(id);
        when(alertRuleService.getAlertRuleById(id)).thenReturn(Optional.of(alertRule));

        ResponseEntity<AlertRule> response = alertRuleController.getAlertRule(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(alertRule, response.getBody());
    }

    @Test
    public void testGetAllAlertRules() {
        when(alertRuleService.getAllAlertRules()).thenReturn(Collections.emptyList());

        ResponseEntity<List<AlertRule>> response = alertRuleController.getAllAlertRules();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    public void testUpdateAlertRule() {
        UUID id = UUID.randomUUID();
        AlertRule alertRule = new AlertRule();
        alertRule.setId(id);
        when(alertRuleService.updateAlertRule(eq(id), any(AlertRule.class))).thenReturn(alertRule);

        ResponseEntity<AlertRule> response = alertRuleController.updateAlertRule(id, alertRule);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(alertRule, response.getBody());
    }

    @Test
    public void testDeleteAlertRule() {
        UUID id = UUID.randomUUID();
        doNothing().when(alertRuleService).deleteAlertRule(id);

        ResponseEntity<Void> response = alertRuleController.deleteAlertRule(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
