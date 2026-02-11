import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class IotDataController {

    @Autowired
    private IotDataService dataService;

    /**
     * Retrieve historical data for a specific device.
     *
     * @param deviceId   the ID of the device
     * @param startTime  optional start time for filtering data
     * @param endTime    optional end time for filtering data
     * @param deviceType optional device type for filtering data
     * @return ResponseEntity containing historical data or appropriate status code
     */
    @GetMapping("/{device_id}/historical-data")
    public ResponseEntity<List<IotDataEntity>> getHistoricalData(
            @PathVariable("device_id") String deviceId,
            @RequestParam(value = "start_time", required = false) String startTime,
            @RequestParam(value = "end_time", required = false) String endTime,
            @RequestParam(value = "device_type", required = false) String deviceType) {
        List<IotDataEntity> historicalData = dataService.getHistoricalData(deviceId, startTime, endTime, deviceType);

        if (historicalData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(historicalData, HttpStatus.OK);
    }
}