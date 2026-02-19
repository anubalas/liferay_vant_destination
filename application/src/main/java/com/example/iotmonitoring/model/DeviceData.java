package com.example.iotmonitoring.model;

import javax.persistence.*;
import java.util.UUID;

/**
 * Entity class representing the data collected from a device.
 */
@Entity
@Table(name = "device_data")
public class DeviceData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "device_id", nullable = false)
    private UUID deviceId;

    @Column(name = "timestamp", nullable = false)
    private long timestamp;

    @Column(name = "data", nullable = false)
    private float data;

    public DeviceData() {}

    public DeviceData(UUID deviceId, long timestamp, float data) {
        this.deviceId = deviceId;
        this.timestamp = timestamp;
        this.data = data;
    }

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float getData() {
        return data;
    }

    public void setData(float data) {
        this.data = data;
    }
}