package com.example.iotmonitoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * ValidationConfig configures validation settings for the application.
 */
@Configuration
public class ValidationConfig {
    /**
     * Provides a LocalValidatorFactoryBean for validation.
     * @return LocalValidatorFactoryBean
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}