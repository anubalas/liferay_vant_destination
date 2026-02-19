package com.example.iotmonitoring.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.example.iotmonitoring.exception.CustomException;

/**
 * Unit tests for DataValidationService.
 */
class DataValidationServiceTest {

    private final DataValidationService dataValidationService = new DataValidationService();

    @Test
    void testValidateIncomingData_ValidJson() throws CustomException {
        String validJson = "{\"key\": \"value\"}";
        assertDoesNotThrow(() -> dataValidationService.validateIncomingData(validJson));
    }

    @Test
    void testValidateIncomingData_InvalidJson() {
        String invalidJson = "{key: value}";
        Exception exception = assertThrows(CustomException.class, () -> {
            dataValidationService.validateIncomingData(invalidJson);
        });
        assertEquals("Invalid data format", exception.getMessage());
    }
}