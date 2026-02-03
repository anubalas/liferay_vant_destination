import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Service class for managing alert rules for devices.
 */
@Service
public class AlertRuleService {

    @Autowired
    private AlertRuleRepository alertRuleRepository;

    /**
     * Create a new alert rule.
     */
    public AlertRule createAlertRule(@Valid AlertRule alertRule) {
        return alertRuleRepository.save(alertRule);
    }

    /**
     * Get all alert rules for a specific device.
     */
    public List<AlertRule> getAlertRulesByDeviceId(UUID deviceId) {
        return alertRuleRepository.findByDeviceId(deviceId);
    }
}