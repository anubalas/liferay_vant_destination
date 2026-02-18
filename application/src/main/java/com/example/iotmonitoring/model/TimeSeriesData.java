package com.example.iotmonitoring.model;

import javax.persistence.*;
import java.util.UUID;
import java.sql.Timestamp;

/**
 * Entity representing time-series data in the IoT Monitoring system.
 */
@Entity
@Table(name = "device_data")
public class TimeSeriesData {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "device_id")
    private UUID deviceId;

    private Timestamp timestamp;
    private String data;

    @Column(name = "created_at")
    private Timestamp createdAt;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}