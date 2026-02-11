import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/devices")
public class IotThresholdController {

    @Autowired
    private IotThresholdService thresholdService;

    // Endpoint to set a threshold for a device
    @PostMapping("/{device_id}/thresholds")
    public ResponseEntity<String> setThreshold(@PathVariable String device_id, @RequestBody Threshold threshold) {
        try {
            thresholdService.setThreshold(device_id, threshold);
            return new ResponseEntity<>("Threshold set successfully", HttpStatus.CREATED);
        } catch (DeviceNotFoundException e) {
            return new ResponseEntity<>("Device not found", HttpStatus.NOT_FOUND);
        } catch (InvalidThresholdException e) {
            return new ResponseEntity<>("Invalid threshold data", HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to remove a threshold for a device
    @DeleteMapping("/{device_id}/thresholds")
    public ResponseEntity<String> removeThreshold(@PathVariable String device_id) {
        try {
            thresholdService.removeThreshold(device_id);
            return new ResponseEntity<>("Threshold removed successfully", HttpStatus.NO_CONTENT);
        } catch (DeviceNotFoundException e) {
            return new ResponseEntity<>("Device not found", HttpStatus.NOT_FOUND);
        }
    }
}