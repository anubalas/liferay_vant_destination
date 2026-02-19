package com.example.iotmonitoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

/**
 * Configuration class for application metrics.
 */
@Configuration
public class MetricsConfig {

    /**
     * Timer for measuring data ingestion performance.
     *
     * @param registry The MeterRegistry to register the timer.
     * @return A Timer instance for data ingestion.
     */
    @Bean
    public Timer dataIngestionTimer(MeterRegistry registry) {
        return Timer.builder("data.ingestion.time")
                .description("Time taken for data ingestion")
                .register(registry);
    }
}