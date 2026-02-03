# IoT Monitoring Microservice

## Overview
The IoT Monitoring Microservice is designed to monitor, collect, store, and analyze data from IoT devices. It is implemented in Java using Spring Boot and utilizes an in-memory database for data storage.

## Features
- Real-time data ingestion from IoT devices via HTTP or MQTT.
- RESTful APIs for device management, data retrieval, and health monitoring.
- Alerting mechanism based on pre-configured thresholds.
- JWT-based authentication and role-based authorization.

## Database Schema
The database schema is documented in the `DatabaseSchemaDocumentation.md` file. It includes tables for devices, device data, and alert rules.

## Getting Started
1. Clone the repository.
2. Navigate to the `application/` directory.
3. Build the project using Maven:
   ```bash
   mvn clean install -DskipTests
   ```
4. Run the application using Docker or your preferred method.

## API Documentation
API endpoints are documented using Swagger. Ensure to check the Swagger configuration in `SwaggerConfig.java` for details.

## Testing
Functional test cases are provided in `functional_test_cases.json` and `functional_test_cases_v2.json`.

## License
This project is licensed under the MIT License.