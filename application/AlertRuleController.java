import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/alert-rules")
@Validated
public class AlertRuleController {

    @Autowired
    private AlertRuleService alertRuleService;

    @PostMapping
    public ResponseEntity<AlertRule> createAlertRule(@Valid @RequestBody AlertRule alertRule) {
        AlertRule createdRule = alertRuleService.createAlertRule(alertRule);
        return ResponseEntity.ok(createdRule);
    }

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<List<AlertRule>> getAlertRulesByDeviceId(@PathVariable UUID deviceId) {
        List<AlertRule> alertRules = alertRuleService.getAlertRulesByDeviceId(deviceId);
        return ResponseEntity.ok(alertRules);
    }
}