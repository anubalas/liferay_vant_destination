import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Unit tests for AlertRuleController.
 */
public class AlertRuleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AlertRuleService alertRuleService;

    @InjectMocks
    private AlertRuleController alertRuleController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(alertRuleController).build();
    }

    @Test
    public void testCreateAlertRule() throws Exception {
        UUID deviceId = UUID.randomUUID();
        AlertRule alertRule = new AlertRule();
        alertRule.setDeviceId(deviceId);
        alertRule.setThreshold(100);
        alertRule.setCondition("greater_than");
        alertRule.setNotificationMethod("email");

        when(alertRuleService.createAlertRule(any(AlertRule.class))).thenReturn(alertRule);

        mockMvc.perform(post("/api/alert-rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"deviceId\": \"" + deviceId + "\", \"threshold\": 100, \"condition\": \"greater_than\", \"notificationMethod\": \"email\"}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deviceId").value(deviceId.toString()));
    }

    @Test
    public void testGetAlertRulesByDeviceId() throws Exception {
        UUID deviceId = UUID.randomUUID();
        List<AlertRule> alertRules = Arrays.asList(new AlertRule(), new AlertRule());

        when(alertRuleService.getAlertRulesByDeviceId(eq(deviceId))).thenReturn(alertRules);

        mockMvc.perform(get("/api/alert-rules/device/{deviceId}", deviceId))
                .andExpect(status().isOk());
    }
}