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
 * Unit tests for DeviceDataController.
 */
public class DeviceDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DeviceDataService deviceDataService;

    @InjectMocks
    private DeviceDataController deviceDataController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(deviceDataController).build();
    }

    @Test
    public void testSaveDeviceData() throws Exception {
        UUID deviceId = UUID.randomUUID();
        String data = "{\"temperature\": 22}";
        DeviceData savedData = new DeviceData();
        savedData.setDeviceId(deviceId);
        savedData.setData(data);

        when(deviceDataService.saveDeviceData(eq(deviceId), anyString())).thenReturn(savedData);

        mockMvc.perform(post("/api/device-data/{deviceId}", deviceId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(data))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deviceId").value(deviceId.toString()));
    }

    @Test
    public void testSaveBulkDeviceData() throws Exception {
        UUID deviceId = UUID.randomUUID();
        List<String> dataList = Arrays.asList("{\"temperature\": 22}", "{\"temperature\": 23}");
        List<DeviceData> savedDataList = Arrays.asList(new DeviceData(), new DeviceData());

        when(deviceDataService.saveBulkDeviceData(eq(deviceId), anyList())).thenReturn(savedDataList);

        mockMvc.perform(post("/api/device-data/{deviceId}/bulk", deviceId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dataList.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetDeviceData() throws Exception {
        UUID deviceId = UUID.randomUUID();
        List<DeviceData> deviceDataList = Arrays.asList(new DeviceData(), new DeviceData());

        when(deviceDataService.getDeviceData(eq(deviceId), any(), any())).thenReturn(deviceDataList);

        mockMvc.perform(get("/api/device-data/{deviceId}/range", deviceId)
                .param("start", "2023-01-01T00:00:00")
                .param("end", "2023-01-02T00:00:00"))
                .andExpect(status().isOk());
    }
}