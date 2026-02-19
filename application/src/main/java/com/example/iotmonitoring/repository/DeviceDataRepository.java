package com.example.iotmonitoring.repository;

import com.example.iotmonitoring.model.DeviceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for DeviceData entity.
 */
@Repository
public interface DeviceDataRepository extends JpaRepository<DeviceData, UUID> {
    /**
     * Find device data by device ID.
     *
     * @param deviceId The ID of the device.
     * @return List of device data associated with the device ID.
     */
    List<DeviceData> findByDeviceId(UUID deviceId);
}
