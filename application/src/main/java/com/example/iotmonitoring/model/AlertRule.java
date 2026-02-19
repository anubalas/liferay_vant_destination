package com.example.iotmonitoring.model;

import javax.persistence.*;
import java.util.UUID;

/**
 * Entity class representing an alert rule for a device.
 */
@Entity
@Table(name = "alert_rules")
public class AlertRule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "device_id", nullable = false)
    private UUID deviceId;

    @Column(name = "threshold", nullable = false)
    private float threshold;

    @Column(name = "condition", nullable = false)
    private String condition;

    @Column(name = "notification_method", nullable = false)
    private String notificationMethod;

    public AlertRule() {}

    public AlertRule(UUID deviceId, float threshold, String condition, String notificationMethod) {
        this.deviceId = deviceId;
        this.threshold = threshold;
        this.condition = condition;
        this.notificationMethod = notificationMethod;
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