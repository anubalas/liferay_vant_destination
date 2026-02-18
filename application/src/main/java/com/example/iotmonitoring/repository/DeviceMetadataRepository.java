package com.example.iotmonitoring.repository;

import com.example.iotmonitoring.model.DeviceMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing device metadata in the database.
 */
@Repository
public interface DeviceMetadataRepository extends JpaRepository<DeviceMetadata, UUID> {
}