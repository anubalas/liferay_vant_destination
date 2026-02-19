package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.AlertRule;
import com.example.iotmonitoring.repository.AlertRuleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the AlertService class.
 */
public class AlertServiceTests {

    @Mock
    private AlertRuleRepository alertRuleRepository;

    @InjectMocks
    private AlertService alertService;

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
    public void testSaveAlertRule() {
        when(alertRuleRepository.save(alertRule)).thenReturn(alertRule);
        AlertRule savedRule = alertService.saveAlertRule(alertRule);
        assertEquals(alertRule.getId(), savedRule.getId());
        verify(alertRuleRepository, times(1)).save(alertRule);
    }

    @Test
    public void testDeleteAlertRule() {
        UUID id = alertRule.getId();
        alertService.deleteAlertRule(id);
        verify(alertRuleRepository, times(1)).deleteById(id);
    }
}