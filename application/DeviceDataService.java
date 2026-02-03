import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Service class for managing DeviceData operations in the IoT Monitoring Microservice.
 */
@Service
public class DeviceDataService {
    private final DeviceDataRepository deviceDataRepository;

    @Autowired
    public DeviceDataService(DeviceDataRepository deviceDataRepository) {
        this.deviceDataRepository = deviceDataRepository;
    }

    /**
     * Saves time-series data from IoT devices.
     * @param deviceId The ID of the device.
     * @param data The data to save.
     * @return The saved DeviceData entity.
     */
    public DeviceData saveDeviceData(@NotNull UUID deviceId, @NotNull String data) {
        DeviceData deviceData = new DeviceData();
        deviceData.setDeviceId(deviceId);
        deviceData.setData(data);
        deviceData.setTimestamp(new Date());
        deviceData.setCreatedAt(new Date());
        return deviceDataRepository.save(deviceData);
    }

    /**
     * Saves a list of time-series data from IoT devices in bulk.
     * @param deviceId The ID of the device.
     * @param dataList The list of data to save.
     * @return A list of saved DeviceData entities.
     */
    public List<DeviceData> saveBulkDeviceData(@NotNull UUID deviceId, @NotNull List<String> dataList) {
        List<DeviceData> savedDataList = new ArrayList<>();
        for (String data : dataList) {
            DeviceData deviceData = new DeviceData();
            deviceData.setDeviceId(deviceId);
            deviceData.setData(data);
            deviceData.setTimestamp(new Date());
            deviceData.setCreatedAt(new Date());
            savedDataList.add(deviceData);
        }
        return deviceDataRepository.saveAll(savedDataList);
    }

    /**
     * Fetches device data for a specific device within a time range.
     * @param deviceId The ID of the device.
     * @param start The start time of the range.
     * @param end The end time of the range.
     * @return A list of DeviceData entities.
     */
    public List<DeviceData> getDeviceData(@NotNull UUID deviceId, @NotNull Date start, @NotNull Date end) {
        return deviceDataRepository.findByDeviceIdAndTimestampBetween(deviceId, start, end);
    }
}