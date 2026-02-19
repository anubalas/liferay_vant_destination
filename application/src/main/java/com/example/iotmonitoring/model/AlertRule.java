package com.example.iotmonitoring.model;

import javax.persistence.*;
import java.util.UUID;

/**
 * Entity representing alert rules for devices.
 */
@Entity
@Table(name = "alert_rules")
public class AlertRule {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    private float threshold;
    private String condition;
    private String notificationMethod;

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

    public float getThreshold() {
        return threshold;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getNotificationMethod() {
        return notificationMethod;
    }

    public void setNotificationMethod(String notificationMethod) {
        this.notificationMethod = notificationMethod;
    }
}
