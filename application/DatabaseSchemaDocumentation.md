# Database Schema Documentation for IoT Monitoring Microservice

## Overview
This document outlines the database schema for the IoT Monitoring Microservice, detailing the tables, their fields, and relationships.

## Tables

### Device
- **id** (UUID, Primary Key): Unique identifier for the device.
- **name** (VARCHAR): Name of the device.
- **type** (VARCHAR): Type of the device (e.g., sensor, actuator).
- **location** (VARCHAR): Physical location of the device.
- **created_at** (TIMESTAMP): Timestamp when the device was created.
- **updated_at** (TIMESTAMP): Timestamp when the device was last updated.

### Device Data
- **id** (UUID, Primary Key): Unique identifier for the data entry.
- **device_id** (UUID, Foreign Key -> Device): Reference to the associated device.
- **timestamp** (TIMESTAMP): Timestamp of the data entry.
- **data** (JSONB): The actual data collected from the device, stored in JSON format.
- **created_at** (TIMESTAMP): Timestamp when the data entry was created.

### Alert Rules
- **id** (UUID, Primary Key): Unique identifier for the alert rule.
- **device_id** (UUID, Foreign Key -> Device): Reference to the associated device.
- **threshold** (FLOAT): The threshold value for triggering an alert.
- **condition** (VARCHAR): The condition that must be met for the alert to trigger (e.g., "greater than", "less than").
- **notification_method** (VARCHAR): The method of notification when the alert is triggered (e.g., email, webhook).

## Relationships
- The **Device** table is the parent table for both the **Device Data** and **Alert Rules** tables, establishing a one-to-many relationship.

## Conclusion
This schema is designed to efficiently store and retrieve data from IoT devices, supporting real-time monitoring and alerting functionalities.