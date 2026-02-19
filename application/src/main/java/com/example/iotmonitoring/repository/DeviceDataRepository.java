package com.example.iotmonitoring.repository;

import com.example.iotmonitoring.model.DeviceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing device data in the database.
 */
@Repository
public interface DeviceDataRepository extends JpaRepository<DeviceData, UUID> {
    Optional<DeviceData> findLatestDataByDeviceId(UUID deviceId);
}