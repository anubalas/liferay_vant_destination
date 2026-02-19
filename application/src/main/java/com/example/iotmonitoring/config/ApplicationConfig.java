package com.example.iotmonitoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * ApplicationConfig class to enable configuration properties and manage different profiles.
 * This class allows for different configurations based on the active profile (e.g., dev, prod).
 */
@Configuration
@EnableConfigurationProperties(ConfigurationProperties.class)
public class ApplicationConfig {

    /**
     * Bean for ConfigurationProperties to be used throughout the application.
     * 
     * @return ConfigurationProperties instance
     */
    @Bean
    public ConfigurationProperties configurationProperties() {
        return new ConfigurationProperties();
    }
}