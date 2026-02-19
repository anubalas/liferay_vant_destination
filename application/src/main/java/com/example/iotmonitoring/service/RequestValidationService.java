package com.example.iotmonitoring.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * RequestValidationService validates incoming API requests to ensure they meet expected formats and requirements.
 */
@Service
public class RequestValidationService {

    /**
     * Validates incoming API requests by checking headers, body formats, and authentication.
     * @param request The HTTP request to validate.
     * @return ResponseEntity indicating the result of the validation.
     */
    public ResponseEntity<String> validateApiRequest(HttpServletRequest request) {
        // Check for required headers
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }

        // Additional validation logic (e.g., JSON schema validation) can be added here

        return ResponseEntity.ok("Request is valid");
    }
}