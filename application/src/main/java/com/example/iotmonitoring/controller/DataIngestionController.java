package com.example.iotmonitoring.controller;

import com.example.iotmonitoring.model.DeviceData;
import com.example.iotmonitoring.service.DataIngestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling data ingestion requests from IoT devices.
 */
@RestController
@RequestMapping("/api/data-ingestion")
public class DataIngestionController {
    private final DataIngestionService dataIngestionService;

    @Autowired
    public DataIngestionController(DataIngestionService dataIngestionService) {
        this.dataIngestionService = dataIngestionService;
    }

    /**
     * Endpoint to ingest data from IoT devices.
     * @param deviceData The data to be ingested.
     * @return ResponseEntity indicating the result of the operation.
     */
    @PostMapping
    public ResponseEntity<Void> ingestData(@RequestBody DeviceData deviceData) {
        dataIngestionService.ingestData(deviceData);
        return ResponseEntity.ok().build();
    }
}