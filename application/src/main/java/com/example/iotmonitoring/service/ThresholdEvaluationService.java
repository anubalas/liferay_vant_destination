package com.example.iotmonitoring.service;

import org.springframework.stereotype.Service;

@Service
public class ThresholdEvaluationService {

    public boolean evaluateThreshold(double value, double threshold) {
        return value > threshold;
    }
}