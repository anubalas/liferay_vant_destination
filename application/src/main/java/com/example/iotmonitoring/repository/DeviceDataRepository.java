package com.example.iotmonitoring.repository;

import com.example.iotmonitoring.model.DeviceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing device data from the database.
 */
@Repository
public interface DeviceDataRepository extends JpaRepository<DeviceData, UUID> {
}