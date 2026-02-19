package com.example.iotmonitoring.exception;

/**
 * Custom exception class for handling errors in the IoT Monitoring service.
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}