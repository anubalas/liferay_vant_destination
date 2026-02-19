package com.example.iotmonitoring.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import com.example.iotmonitoring.model.DeviceData;
import java.util.UUID;

/**
 * Unit tests for TimeSeriesRepository.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class TimeSeriesRepositoryTest {

    @Autowired
    private TimeSeriesRepository timeSeriesRepository;

    @BeforeEach
    void setUp() {
        timeSeriesRepository.deleteAll(); // Clear the repository before each test
    }

    @Test
    void testSaveAndFindDeviceData() {
        DeviceData deviceData = new DeviceData();
        deviceData.setId(UUID.randomUUID());
        deviceData.setDeviceId(UUID.randomUUID());
        deviceData.setTimestamp(System.currentTimeMillis());
        deviceData.setData("{\"value\": 100}");
        timeSeriesRepository.save(deviceData);

        DeviceData foundData = timeSeriesRepository.findById(deviceData.getId()).orElse(null);
        assertNotNull(foundData);
        assertEquals(deviceData.getData(), foundData.getData());
    }
}