package com.example.iotmonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.iotmonitoring.model.Device;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {
}