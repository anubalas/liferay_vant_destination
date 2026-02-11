import org.springframework.stereotype.Service;

@Service
public class IotNoThresholdHandler {
    // Method to handle cases where no thresholds are set
    public void handleNoThreshold(String deviceId) {
        // Implement logic for no thresholds
        System.out.println("No thresholds set for device: " + deviceId);
    }
}