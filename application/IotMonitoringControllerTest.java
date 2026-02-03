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
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Unit tests for IotMonitoringController.
 */
public class IotMonitoringControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DeviceService deviceService;

    @Mock
    private DeviceDataService deviceDataService;

    @InjectMocks
    private IotMonitoringController iotMonitoringController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(iotMonitoringController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testIngestData() throws Exception {
        String deviceId = "123e4567-e89b-12d3-a456-426614174000";
        String data = "{\"temperature\": 22}";
        IoTDataPayload payload = new IoTDataPayload();
        payload.setDeviceId(deviceId);
        payload.setData(data);

        mockMvc.perform(post("/api/iot/data")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testRegisterDevice() throws Exception {
        Device device = new Device();
        device.setId(UUID.randomUUID());
        device.setName("Device1");

        when(deviceService.registerDevice(any(Device.class))).thenReturn(device);

        mockMvc.perform(post("/api/iot/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(device)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCheckDeviceStatus() throws Exception {
        String deviceId = "123e4567-e89b-12d3-a456-426614174000";
        String status = "active";

        when(deviceService.getDeviceStatus(any(UUID.class))).thenReturn(status);

        mockMvc.perform(get("/api/iot/devices/{deviceId}/status", deviceId))
                .andExpect(status().isOk())
                .andExpect(content().string(status));
    }
}