package com.example.iotmonitoring.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Unit tests for MqttDataIngestionService.
 */
class MqttDataIngestionServiceTest {

    @InjectMocks
    private MqttDataIngestionService mqttDataIngestionService;

    @Mock
    private MqttMessage mqttMessage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMessageArrived_ValidJson() throws Exception {
        String validJson = "{\"key\": \"value\"}";
        when(mqttMessage.getPayload()).thenReturn(validJson.getBytes());
        assertDoesNotThrow(() -> mqttDataIngestionService.messageArrived("topic", mqttMessage));
    }

    @Test
    void testMessageArrived_InvalidJson() throws Exception {
        String invalidJson = "{key: value}";
        when(mqttMessage.getPayload()).thenReturn(invalidJson.getBytes());
        Exception exception = assertThrows(CustomException.class, () -> {
            mqttDataIngestionService.messageArrived("topic", mqttMessage);
        });
        assertEquals("Invalid data format", exception.getMessage());
    }
}