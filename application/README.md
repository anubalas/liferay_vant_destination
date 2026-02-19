# IoT Monitoring Microservice Configuration Guide

## Configuration Properties

This document outlines the configuration properties used in the IoT Monitoring Microservice.

### Application Properties
- **app.databaseUrl**: The URL for the database connection. (e.g., `jdbc:h2:mem:testdb`)
- **app.databaseUsername**: The username for the database. (e.g., `sa`)
- **app.databasePassword**: The password for the database. (leave blank for H2 in-memory database)
- **app.apiKey**: The API key for external services. (e.g., `your_api_key_here`)

### Profiles
- **Development**: Use `application-dev.properties` for development-specific configurations.
- **Production**: Use `application-prod.properties` for production-specific configurations.

### Security Best Practices
- Ensure sensitive information is stored securely and not hard-coded in the application.
- Use environment variables or a secrets management tool for production environments.

### Validation
The application will fail to start if critical configuration properties are missing or invalid, providing clear error messages for debugging.