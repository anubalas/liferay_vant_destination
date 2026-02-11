import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/devices")
public class IotDataDeviceController {

    @Autowired
    private IotDataService iotDataService;

    // Endpoint for device registration
    @PostMapping
    public ResponseEntity<String> registerDevice(@RequestBody IotDataPayload devicePayload) {
        // Validate and register device
        boolean isRegistered = iotDataService.registerDevice(devicePayload);
        if (isRegistered) {
            return new ResponseEntity<>("Device registered successfully!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Device registration failed!", HttpStatus.BAD_REQUEST);
    }

    // Endpoint for updating device information
    @PutMapping("/{device_id}")
    public ResponseEntity<String> updateDevice(@PathVariable String device_id, @RequestBody IotDataPayload devicePayload) {
        // Validate and update device
        boolean isUpdated = iotDataService.updateDevice(device_id, devicePayload);
        if (isUpdated) {
            return new ResponseEntity<>("Device updated successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Device not found!", HttpStatus.NOT_FOUND);
    }

    // Endpoint for deleting a device
    @DeleteMapping("/{device_id}")
    public ResponseEntity<String> deleteDevice(@PathVariable String device_id) {
        // Validate and delete device
        boolean isDeleted = iotDataService.deleteDevice(device_id);
        if (isDeleted) {
            return new ResponseEntity<>("Device deleted successfully!", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Device not found!", HttpStatus.NOT_FOUND);
    }

    // Endpoint for fetching device details
    @GetMapping("/{device_id}")
    public ResponseEntity<IotDataPayload> getDevice(@PathVariable String device_id) {
        // Fetch device details
        IotDataPayload deviceDetails = iotDataService.getDevice(device_id);
        if (deviceDetails != null) {
            return new ResponseEntity<>(deviceDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}