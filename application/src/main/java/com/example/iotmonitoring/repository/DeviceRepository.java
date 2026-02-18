package com.example.iotmonitoring.repository;

import com.example.iotmonitoring.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {
    // Additional query methods can be defined here if needed
}