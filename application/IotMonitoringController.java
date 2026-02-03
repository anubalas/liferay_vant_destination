import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.UUID;

/**
 * Controller for handling HTTP requests for IoT Monitoring.
 */
@RestController
@RequestMapping("/api/iot")
public class IotMonitoringController {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final DataPreprocessor dataPreprocessor = new DataPreprocessor();
    private final DeviceService deviceService;
    private final DeviceDataService deviceDataService;

    public IotMonitoringController(DeviceService deviceService, DeviceDataService deviceDataService) {
        this.deviceService = deviceService;
        this.deviceDataService = deviceDataService;
    }

    /**
     * Ingests data from IoT devices via HTTP POST.
     * 
     * @param payload JSON payload containing device data.
     * @return ResponseEntity with appropriate HTTP status.
     */
    @PostMapping("/data")
    public ResponseEntity<String> ingestData(@Valid @RequestBody IoTDataPayload payload) {
        // Preprocess the incoming data
        JsonNode formattedData = dataPreprocessor.preprocessData(payload.getData());
        if (formattedData != null) {
            // Save the data to the in-memory database
            deviceDataService.saveDeviceData(UUID.fromString(payload.getDeviceId()), formattedData.toString());
            return new ResponseEntity<>("Data ingested successfully", HttpStatus.CREATED);
        } else {
            // Log error for invalid format
            System.err.println("Invalid data format: " + payload.getData());
            return new ResponseEntity<>("Invalid data format", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Registers a new device.
     * @param device The device to register.
     * @return ResponseEntity with the registered device.
     */
    @PostMapping("/devices")
    public ResponseEntity<Device> registerDevice(@Valid @RequestBody Device device) {
        Device registeredDevice = deviceService.registerDevice(device);
        return new ResponseEntity<>(registeredDevice, HttpStatus.CREATED);
    }

    /**
     * Checks the health status of a device.
     * @param deviceId The ID of the device to check.
     * @return ResponseEntity with the device status.
     */
    @GetMapping("/devices/{deviceId}/status")
    public ResponseEntity<String> checkDeviceStatus(@PathVariable String deviceId) {
        String status = deviceService.getDeviceStatus(UUID.fromString(deviceId));
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    /**
     * Checks the last communication time of a device.
     * @param deviceId The ID of the device to check.
     * @return ResponseEntity with the last communication time.
     */
    @GetMapping("/devices/{deviceId}/last-communication")
    public ResponseEntity<String> checkLastCommunication(@PathVariable String deviceId) {
        String lastCommunicationTime = deviceDataService.getLastCommunicationTime(UUID.fromString(deviceId));
        return new ResponseEntity<>(lastCommunicationTime, HttpStatus.OK);
    }

    /**
     * Validates if the incoming payload is a valid JSON format.
     * @param data The data to validate.
     * @return true if valid JSON, false otherwise.
     */
    private boolean isValidJson(String data) {
        try {
            objectMapper.readTree(data);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}

/**
 * Payload class for IoT data.
 */
class IoTDataPayload {
    private String deviceId;
    private String data;

    // Getters and Setters
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}