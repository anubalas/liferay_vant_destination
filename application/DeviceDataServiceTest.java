import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Unit tests for DeviceDataService.
 */
public class DeviceDataServiceTest {

    @Mock
    private DeviceDataRepository deviceDataRepository;

    @InjectMocks
    private DeviceDataService deviceDataService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDeviceData() {
        UUID deviceId = UUID.randomUUID();
        String data = "{\"temperature\": 22}";
        DeviceData deviceData = new DeviceData();
        deviceData.setDeviceId(deviceId);
        deviceData.setData(data);
        deviceData.setTimestamp(new Date());
        deviceData.setCreatedAt(new Date());

        when(deviceDataRepository.save(any(DeviceData.class))).thenReturn(deviceData);

        DeviceData savedData = deviceDataService.saveDeviceData(deviceId, data);

        assertNotNull(savedData);
        assertEquals(deviceId, savedData.getDeviceId());
        assertEquals(data, savedData.getData());
        verify(deviceDataRepository, times(1)).save(any(DeviceData.class));
    }

    @Test
    public void testSaveBulkDeviceData() {
        UUID deviceId = UUID.randomUUID();
        List<String> dataList = new ArrayList<>();
        dataList.add("{\"temperature\": 22}");
        dataList.add("{\"temperature\": 23}");

        List<DeviceData> savedDataList = new ArrayList<>();
        for (String data : dataList) {
            DeviceData deviceData = new DeviceData();
            deviceData.setDeviceId(deviceId);
            deviceData.setData(data);
            deviceData.setTimestamp(new Date());
            deviceData.setCreatedAt(new Date());
            savedDataList.add(deviceData);
        }

        when(deviceDataRepository.saveAll(anyList())).thenReturn(savedDataList);

        List<DeviceData> result = deviceDataService.saveBulkDeviceData(deviceId, dataList);

        assertEquals(2, result.size());
        verify(deviceDataRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void testGetDeviceData() {
        UUID deviceId = UUID.randomUUID();
        List<DeviceData> deviceDataList = new ArrayList<>();
        deviceDataList.add(new DeviceData());
        deviceDataList.add(new DeviceData());

        when(deviceDataRepository.findByDeviceIdAndTimestampBetween(eq(deviceId), any(), any())).thenReturn(deviceDataList);

        List<DeviceData> result = deviceDataService.getDeviceData(deviceId, new Date(), new Date());

        assertEquals(2, result.size());
        verify(deviceDataRepository, times(1)).findByDeviceIdAndTimestampBetween(eq(deviceId), any(), any());
    }
}