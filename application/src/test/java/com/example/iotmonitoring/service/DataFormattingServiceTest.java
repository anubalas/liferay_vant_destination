package com.example.iotmonitoring.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for DataFormattingService.
 */
class DataFormattingServiceTest {

    private final DataFormattingService dataFormattingService = new DataFormattingService();

    @Test
    void testFormatDataForStorage() {
        String validatedData = "{\"key\": \"value\"}";
        String formattedData = dataFormattingService.formatDataForStorage(validatedData);
        assertNotNull(formattedData);
        // Add more assertions based on expected formatting logic
    }
}