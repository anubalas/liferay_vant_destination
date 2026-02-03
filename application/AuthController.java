import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam @NotBlank String username, @RequestParam @NotBlank String password) {
        // Authenticate user and generate token
        String token = jwtTokenProvider.generateToken(username);
        return ResponseEntity.ok(token);
    }
}