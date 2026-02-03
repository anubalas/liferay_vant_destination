import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing DeviceData entities in the IoT Monitoring Microservice.
 */
@Repository
public interface DeviceDataRepository extends JpaRepository<DeviceData, UUID> {
    // Method to find device data by device ID and timestamp range
    List<DeviceData> findByDeviceIdAndTimestampBetween(UUID deviceId, Date start, Date end);
}