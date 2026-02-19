package com.example.iotmonitoring.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.iotmonitoring.service.TimeSeriesService;
import java.util.List;

@RestController
@RequestMapping("/devices")
public class DataQueryController {

    @Autowired
    private TimeSeriesService timeSeriesService;

    // Retrieve historical data for a specific device
    @GetMapping("/{id}/data")
    public ResponseEntity<List<DeviceData>> getDeviceData(@PathVariable String id,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end) {
        // Logic to retrieve data
        return ResponseEntity.ok(timeSeriesService.getDeviceData(id, start, end));
    }

    // Retrieve historical data for all devices
    @GetMapping("/data")
    public ResponseEntity<List<DeviceData>> getAllDeviceData(@RequestParam(required = false) String type) {
        // Logic to retrieve all data
        return ResponseEntity.ok(timeSeriesService.getAllDeviceData(type));
    }
}