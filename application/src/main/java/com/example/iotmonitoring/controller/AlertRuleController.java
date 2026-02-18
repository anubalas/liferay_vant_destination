package com.example.iotmonitoring.controller;

import com.example.iotmonitoring.model.AlertRule;
import com.example.iotmonitoring.service.AlertRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

/**
 * Controller for managing alert rules.
 */
@RestController
@RequestMapping("/alert-rules")
public class AlertRuleController {

    @Autowired
    private AlertRuleService alertRuleService;

    @PostMapping
    public ResponseEntity<AlertRule> createAlertRule(@RequestBody AlertRule alertRule) {
        AlertRule createdRule = alertRuleService.createAlertRule(alertRule);
        return new ResponseEntity<>(createdRule, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertRule> getAlertRule(@PathVariable UUID id) {
        return alertRuleService.getAlertRuleById(id)
                .map(rule -> new ResponseEntity<>(rule, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<AlertRule>> getAllAlertRules() {
        List<AlertRule> rules = alertRuleService.getAllAlertRules();
        return new ResponseEntity<>(rules, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertRule> updateAlertRule(@PathVariable UUID id, @RequestBody AlertRule alertRule) {
        AlertRule updatedRule = alertRuleService.updateAlertRule(id, alertRule);
        return new ResponseEntity<>(updatedRule, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlertRule(@PathVariable UUID id) {
        alertRuleService.deleteAlertRule(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}