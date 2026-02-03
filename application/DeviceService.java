import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Service class for managing Device operations in the IoT Monitoring Microservice.
 */
@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    /**
     * Registers a new device in the system.
     * @param device The device to register.
     * @return The registered device.
     */
    public Device registerDevice(Device device) {
        device.setCreatedAt(new Date());
        device.setUpdatedAt(new Date());
        return deviceRepository.save(device);
    }

    /**
     * Fetches a device by its ID.
     * @param id The ID of the device.
     * @return An Optional containing the device if found, or empty if not.
     */
    public Optional<Device> getDeviceById(UUID id) {
        return deviceRepository.findById(id);
    }
}