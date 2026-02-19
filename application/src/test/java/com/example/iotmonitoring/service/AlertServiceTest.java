package com.example.iotmonitoring.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.UUID;

/**
 * Unit tests for AlertService.
 */
class AlertServiceTest {

    @InjectMocks
    private AlertService alertService;

    @Mock
    private AlertRuleRepository alertRuleRepository;

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private DeviceDataRepository deviceDataRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDefineAlertRule_ValidInput() {
        UUID deviceId = UUID.randomUUID();
        alertService.defineAlertRule(deviceId, 100, "greater than", "email");
        // Add assertions to verify the alert rule was saved
    }

    @Test
    void testDefineAlertRule_InvalidDeviceId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            alertService.defineAlertRule(UUID.randomUUID(), 100, "greater than", "email");
        });
        assertEquals("Device ID does not exist.", exception.getMessage());
    }
}