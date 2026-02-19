package com.example.iotmonitoring.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Unit tests for HttpDataIngestionService.
 */
class HttpDataIngestionServiceTest {

    @InjectMocks
    private HttpDataIngestionService httpDataIngestionService;

    @Mock
    private CustomException customException;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIngestData_ValidJson() {
        String validJson = "{\"key\": \"value\"}";
        assertDoesNotThrow(() -> httpDataIngestionService.ingestData(validJson));
    }

    @Test
    void testIngestData_InvalidJson() {
        String invalidJson = "{key: value}";
        Exception exception = assertThrows(CustomException.class, () -> {
            httpDataIngestionService.ingestData(invalidJson);
        });
        assertEquals("Invalid data format", exception.getMessage());
    }
}