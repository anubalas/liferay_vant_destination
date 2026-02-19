package com.example.iotmonitoring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ConfigurationProperties class to manage environment variables securely.
 * This class binds properties from application.properties or application-{profile}.properties
 * to a Java object for easy access throughout the application.
 */
@Component
@ConfigurationProperties(prefix = "app")
public class ConfigurationProperties {

    @NotBlank(message = "Database URL must not be blank")
    private String databaseUrl;

    @NotBlank(message = "Database Username must not be blank")
    private String databaseUsername;

    @NotBlank(message = "Database Password must not be blank")
    private String databasePassword;

    @NotNull(message = "API Key must not be null")
    private String apiKey;

    // Getters and Setters

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}