package com.example.iotmonitoring.config;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetrics;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Prometheus metrics.
 */
@Configuration
@EnablePrometheusEndpoint
@EnableSpringBootMetrics
public class PrometheusConfig {
}