/application/src/main/java/com/example/iotmonitoring/controller/DeviceDataController.java

package com.example.iotmonitoring.controller;

import com.example.iotmonitoring.model.DeviceData;
import com.example.iotmonitoring.service.DeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing time-series data in the IoT Monitoring system.
 */
@RestController
@RequestMapping("/api/device-data")
public class DeviceDataController {
    @Autowired
    private DeviceDataService deviceDataService;

    @GetMapping
    public List<DeviceData> getAllDeviceData() {
        return deviceDataService.getAllDeviceData();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceData> getDeviceDataById(@PathVariable UUID id) {
        return deviceDataService.getDeviceDataById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DeviceData createDeviceData(@RequestBody DeviceData deviceData) {
        return deviceDataService.createDeviceData(deviceData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeviceData(@PathVariable UUID id) {
        deviceDataService.deleteDeviceData(id);
        return ResponseEntity.noContent().build();
    }
}