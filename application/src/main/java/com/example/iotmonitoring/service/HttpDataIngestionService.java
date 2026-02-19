package com.example.iotmonitoring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Service for handling data ingestion via HTTP.
 */
@Service
@RestController
public class HttpDataIngestionService {

    private final ConcurrentLinkedQueue<String> dataQueue = new ConcurrentLinkedQueue<>();

    @Value("${data.ingestion.http.interval}")
    private long ingestionInterval;

    /**
     * Receives JSON data from IoT devices and stores it in memory.
     * @param data JSON data from the device.
     */
    @PostMapping("/ingest/http")
    public void ingestData(@Valid @RequestBody String data) {
        // Validate and process incoming data
        if (isValidJson(data)) {
            dataQueue.add(data);
            // Logic to handle data storage and processing
        } else {
            throw new CustomException("Invalid data format");
        }
    }

    /**
     * Validates the incoming JSON data format.
     * @param data JSON data as a string.
     * @return true if valid, false otherwise.
     */
    private boolean isValidJson(String data) {
        // Implement JSON validation logic
        return true;
    }
}