package com.example.iotmonitoring.model;

import javax.persistence.*;
import java.util.UUID;
import java.time.LocalDateTime;

/**
 * Entity representing time-series data for a device.
 */
@Entity
@Table(name = "device_data")
public class DeviceData {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    private LocalDateTime timestamp;
    private String data;
    private LocalDateTime createdAt;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
