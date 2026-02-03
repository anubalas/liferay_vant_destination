import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Device entities in the IoT Monitoring Microservice.
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {
    // Additional query methods can be defined here if needed
}
