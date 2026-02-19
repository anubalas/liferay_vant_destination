package com.example.iotmonitoring.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * RequestValidationServiceTest contains unit tests for the RequestValidationService.
 */
public class RequestValidationServiceTest {

    private final RequestValidationService requestValidationService = new RequestValidationService();

    @Test
    public void testValidateApiRequest_ValidRequest() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer valid_token");

        ResponseEntity<String> response = requestValidationService.validateApiRequest(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Request is valid", response.getBody());
    }

    @Test
    public void testValidateApiRequest_MissingAuthorization() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader("Authorization")).thenReturn(null);

        ResponseEntity<String> response = requestValidationService.validateApiRequest(request);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Unauthorized access", response.getBody());
    }
}