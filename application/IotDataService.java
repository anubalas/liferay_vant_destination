import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class IotDataService {
    private Map<String, IotDataPayload> deviceDatabase = new HashMap<>();

    // Register a new device
    public boolean registerDevice(IotDataPayload devicePayload) {
        if (devicePayload.getDeviceId() == null || deviceDatabase.containsKey(devicePayload.getDeviceId())) {
            return false; // Invalid or duplicate device ID
        }
        deviceDatabase.put(devicePayload.getDeviceId(), devicePayload);
        return true;
    }

    // Update existing device information
    public boolean updateDevice(String deviceId, IotDataPayload devicePayload) {
        if (!deviceDatabase.containsKey(deviceId)) {
            return false; // Device not found
        }
        deviceDatabase.put(deviceId, devicePayload);
        return true;
    }

    // Delete a device
    public boolean deleteDevice(String deviceId) {
        if (!deviceDatabase.containsKey(deviceId)) {
            return false; // Device not found
        }
        deviceDatabase.remove(deviceId);
        return true;
    }

    // Fetch device details
    public IotDataPayload getDevice(String deviceId) {
        return deviceDatabase.get(deviceId); // Returns null if not found
    }
}