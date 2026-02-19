/application/src/main/java/com/example/iotmonitoring/controller/DataIngestionController.java

package com.example.iotmonitoring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Controller for handling data ingestion from IoT devices via HTTP.
 */
@RestController
@RequestMapping("/api/devices")
public class DataIngestionController {

    /**
     * Endpoint to receive device data via HTTP POST.
     *
     * @param deviceData List of device data to be ingested.
     * @return ResponseEntity indicating the result of the operation.
     */
    @PostMapping("/data")
    public ResponseEntity<String> ingestData(@Valid @RequestBody List<@NotNull DeviceData> deviceData) {
        // TODO: Implement data ingestion logic
        return new ResponseEntity<>("Data ingested successfully", HttpStatus.OK);
    }

    // Define the DeviceData class or import it if it's in another file
    public static class DeviceData {
        // Define fields for device data (e.g., timestamp, value, metadata)
        private String timestamp;
        private double value;
        private String metadata;

        // Getters and Setters
        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public String getMetadata() {
            return metadata;
        }

        public void setMetadata(String metadata) {
            this.metadata = metadata;
        }
    }
}