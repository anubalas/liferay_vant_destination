import org.springframework.stereotype.Service;

@Service
public class IotAlertService {
    // Method to generate alerts when thresholds are breached
    public void generateAlert(String deviceId, double breachedValue) {
        // Implement alert generation logic
        System.out.println("Alert! Device " + deviceId + " breached threshold with value: " + breachedValue);
        // Call notification method here
    }
}