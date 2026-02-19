package com.example.iotmonitoring.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import com.example.iotmonitoring.model.Device;
import java.util.UUID;

/**
 * Unit tests for DeviceRepository.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class DeviceRepositoryTest {

    @Autowired
    private DeviceRepository deviceRepository;

    @BeforeEach
    void setUp() {
        deviceRepository.deleteAll(); // Clear the repository before each test
    }

    @Test
    void testSaveAndFindDevice() {
        Device device = new Device();
        device.setId(UUID.randomUUID());
        device.setName("Test Device");
        device.setType("Sensor");
        device.setLocation("Room 1");
        deviceRepository.save(device);

        Device foundDevice = deviceRepository.findById(device.getId()).orElse(null);
        assertNotNull(foundDevice);
        assertEquals(device.getName(), foundDevice.getName());
    }
}