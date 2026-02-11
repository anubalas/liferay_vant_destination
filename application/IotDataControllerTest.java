import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class IotDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IotDataService iotDataService;

    @InjectMocks
    private IotDataDeviceController iotDataDeviceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterDevice() throws Exception {
        IotDataPayload devicePayload = new IotDataPayload();
        devicePayload.setDeviceId("device1");
        devicePayload.setDeviceType("sensor");
        devicePayload.setMetadata("metadata");

        when(iotDataService.registerDevice(any(IotDataPayload.class))).thenReturn(true);

        mockMvc.perform(post("/api/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"deviceId\":\"device1\",\"deviceType\":\"sensor\",\"metadata\":\"metadata\"}")
        )
        .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateDevice() throws Exception {
        IotDataPayload devicePayload = new IotDataPayload();
        devicePayload.setDeviceId("device1");
        devicePayload.setDeviceType("sensor");
        devicePayload.setMetadata("metadata");

        when(iotDataService.updateDevice(eq("device1"), any(IotDataPayload.class))).thenReturn(true);

        mockMvc.perform(put("/api/devices/device1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"deviceType\":\"sensor\",\"metadata\":\"updated metadata\"}")
        )
        .andExpect(status().isOk());
    }

    @Test
    public void testDeleteDevice() throws Exception {
        when(iotDataService.deleteDevice(eq("device1"))).thenReturn(true);

        mockMvc.perform(delete("/api/devices/device1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetDevice() throws Exception {
        IotDataPayload devicePayload = new IotDataPayload();
        devicePayload.setDeviceId("device1");
        devicePayload.setDeviceType("sensor");
        devicePayload.setMetadata("metadata");

        when(iotDataService.getDevice(eq("device1"))).thenReturn(devicePayload);

        mockMvc.perform(get("/api/devices/device1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}