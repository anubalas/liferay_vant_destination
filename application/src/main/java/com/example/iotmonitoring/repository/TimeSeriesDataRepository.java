package com.example.iotmonitoring.repository;

import com.example.iotmonitoring.model.TimeSeriesData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing time-series data in the database.
 */
@Repository
public interface TimeSeriesDataRepository extends JpaRepository<TimeSeriesData, UUID> {
}