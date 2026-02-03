import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Unit tests for AlertRuleService.
 */
public class AlertRuleServiceTest {

    @Mock
    private AlertRuleRepository alertRuleRepository;

    @InjectMocks
    private AlertRuleService alertRuleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAlertRule() {
        AlertRule alertRule = new AlertRule();
        alertRule.setDeviceId(UUID.randomUUID());
        alertRule.setThreshold(100);
        alertRule.setCondition("greater_than");
        alertRule.setNotificationMethod("email");

        when(alertRuleRepository.save(any(AlertRule.class))).thenReturn(alertRule);

        AlertRule createdRule = alertRuleService.createAlertRule(alertRule);

        assertNotNull(createdRule);
        assertEquals(alertRule.getDeviceId(), createdRule.getDeviceId());
        verify(alertRuleRepository, times(1)).save(any(AlertRule.class));
    }

    @Test
    public void testGetAlertRulesByDeviceId() {
        UUID deviceId = UUID.randomUUID();
        List<AlertRule> alertRules = new ArrayList<>();
        alertRules.add(new AlertRule());
        alertRules.add(new AlertRule());

        when(alertRuleRepository.findByDeviceId(deviceId)).thenReturn(alertRules);

        List<AlertRule> result = alertRuleService.getAlertRulesByDeviceId(deviceId);

        assertEquals(2, result.size());
        verify(alertRuleRepository, times(1)).findByDeviceId(deviceId);
    }
}