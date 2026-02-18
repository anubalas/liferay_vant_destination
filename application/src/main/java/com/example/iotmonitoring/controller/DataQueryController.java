package com.example.iotmonitoring.controller;

import com.example.iotmonitoring.model.DeviceData;
import com.example.iotmonitoring.service.DataQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller for handling data query requests for IoT devices.
 */
@RestController
@RequestMapping("/api/data")
public class DataQueryController {

    private final DataQueryService dataQueryService;

    @Autowired
    public DataQueryController(DataQueryService dataQueryService) {
        this.dataQueryService = dataQueryService;
    }

    /**
     * Retrieve historical data for a specific device or group of devices.
     *
     * @param deviceIds List of device IDs to query.
     * @param startTime Start timestamp for the data retrieval.
     * @param endTime End timestamp for the data retrieval.
     * @param deviceType Optional device type filter.
     * @param threshold Optional threshold filter.
     * @return ResponseEntity containing the historical data.
     */
    @GetMapping("/historical")
    public ResponseEntity<List<DeviceData>> getHistoricalData(
            @RequestParam List<String> deviceIds,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime,
            @RequestParam(required = false) String deviceType,
            @RequestParam(required = false) Float threshold) {
        List<DeviceData> data = dataQueryService.getHistoricalData(deviceIds, startTime, endTime, deviceType, threshold);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}