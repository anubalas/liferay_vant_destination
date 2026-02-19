/application/src/main/java/com/example/iotmonitoring/controller/DeviceController.java

package com.example.iotmonitoring.controller;

import com.example.iotmonitoring.model.Device;
import com.example.iotmonitoring.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing devices in the IoT Monitoring system.
 */
@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable UUID id) {
        return deviceService.getDeviceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Device createDevice(@RequestBody Device device) {
        return deviceService.createDevice(device);
    }

    @PutMapping("/{id}")
    public Device updateDevice(@PathVariable UUID id, @RequestBody Device device) {
        return deviceService.updateDevice(id, device);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable UUID id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}