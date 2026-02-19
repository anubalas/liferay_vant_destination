package com.example.iotmonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.iotmonitoring.model.DeviceData;
import java.util.UUID;

public interface TimeSeriesRepository extends JpaRepository<DeviceData, UUID> {
}