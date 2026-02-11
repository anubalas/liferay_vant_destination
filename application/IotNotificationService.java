import org.springframework.stereotype.Service;

@Service
public class IotNotificationService {
    // Method to send notifications
    public void sendNotification(String message) {
        // Implement notification sending logic (e.g., email, SMS)
        System.out.println("Notification sent: " + message);
    }
}