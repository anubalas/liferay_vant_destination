import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class IotThresholdService {
    private Map<String, Threshold> thresholds = new HashMap<>();

    // Method to set a threshold for a device
    public void setThreshold(String deviceId, Threshold threshold) throws DeviceNotFoundException, InvalidThresholdException {
        // Validate device ID and threshold data
        if (!isValidDevice(deviceId)) {
            throw new DeviceNotFoundException();
        }
        if (!isValidThreshold(threshold)) {
            throw new InvalidThresholdException();
        }
        thresholds.put(deviceId, threshold);
    }

    // Method to remove a threshold for a device
    public void removeThreshold(String deviceId) throws DeviceNotFoundException {
        if (!thresholds.containsKey(deviceId)) {
            throw new DeviceNotFoundException();
        }
        thresholds.remove(deviceId);
    }

    // Placeholder for device validation
    private boolean isValidDevice(String deviceId) {
        // Implement device validation logic
        return true;
    }

    // Placeholder for threshold validation
    private boolean isValidThreshold(Threshold threshold) {
        // Implement threshold validation logic
        return true;
    }
}