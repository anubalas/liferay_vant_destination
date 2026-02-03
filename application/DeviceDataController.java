import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Controller for handling device data operations in the IoT Monitoring Microservice.
 */
@RestController
@RequestMapping("/api/device-data")
@Validated
public class DeviceDataController {

    @Autowired
    private DeviceDataService deviceDataService;

    /**
     * Saves device data for a specific device.
     * @param deviceId The ID of the device.
     * @param data The data to save.
     * @return ResponseEntity containing the saved DeviceData.
     */
    @PostMapping("/{deviceId}")
    public ResponseEntity<DeviceData> saveDeviceData(@PathVariable UUID deviceId, @Valid @RequestBody @NotNull String data) {
        DeviceData savedData = deviceDataService.saveDeviceData(deviceId, data);
        return ResponseEntity.ok(savedData);
    }

    /**
     * Saves bulk device data for a specific device.
     * @param deviceId The ID of the device.
     * @param dataList The list of data to save.
     * @return ResponseEntity containing the list of saved DeviceData.
     */
    @PostMapping("/{deviceId}/bulk")
    public ResponseEntity<List<DeviceData>> saveBulkDeviceData(@PathVariable UUID deviceId, @Valid @RequestBody @NotNull List<String> dataList) {
        List<DeviceData> savedDataList = deviceDataService.saveBulkDeviceData(deviceId, dataList);
        return ResponseEntity.ok(savedDataList);
    }

    /**
     * Retrieves device data for a specific device within a time range.
     * @param deviceId The ID of the device.
     * @param start The start time of the range.
     * @param end The end time of the range.
     * @return ResponseEntity containing the list of DeviceData.
     */
    @GetMapping("/{deviceId}/range")
    public ResponseEntity<List<DeviceData>> getDeviceData(@PathVariable UUID deviceId, @RequestParam @NotNull Date start, @RequestParam @NotNull Date end) {
        List<DeviceData> deviceData = deviceDataService.getDeviceData(deviceId, start, end);
        return ResponseEntity.ok(deviceData);
    }
}