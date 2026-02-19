package com.example.iotmonitoring.controller;

import com.example.iotmonitoring.model.AlertRule;
import com.example.iotmonitoring.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing alert rules.
 */
@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    /**
     * Create or update an alert rule.
     */
    @PostMapping
    public ResponseEntity<AlertRule> createOrUpdateAlertRule(@RequestBody AlertRule alertRule) {
        AlertRule savedRule = alertService.saveAlertRule(alertRule);
        return ResponseEntity.ok(savedRule);
    }

    /**
     * Delete an alert rule by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlertRule(@PathVariable UUID id) {
        alertService.deleteAlertRule(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieve all alert rules.
     */
    @GetMapping
    public ResponseEntity<List<AlertRule>> getAllAlertRules() {
        List<AlertRule> alertRules = alertService.getAllAlertRules();
        return ResponseEntity.ok(alertRules);
    }
}