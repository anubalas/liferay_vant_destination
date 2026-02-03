import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * JwtTokenProviderTest tests the functionality of the JwtTokenProvider class,
 * validating token generation and validation.
 */
public class JwtTokenProviderTest {

    private final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    /**
     * Test for token generation.
     */
    @Test
    public void testGenerateToken() {
        String token = jwtTokenProvider.generateToken("testUser");
        assertNotNull(token);
    }

    /**
     * Test for token validation.
     */
    @Test
    public void testValidateToken() {
        String token = jwtTokenProvider.generateToken("testUser");
        assertTrue(jwtTokenProvider.validateToken(token));
    }

    /**
     * Test for invalid token validation.
     */
    @Test
    public void testValidateInvalidToken() {
        assertFalse(jwtTokenProvider.validateToken("invalidToken"));
    }

    /**
     * Test for extracting username from token.
     */
    @Test
    public void testGetUsernameFromToken() {
        String token = jwtTokenProvider.generateToken("testUser");
        String username = jwtTokenProvider.getUsernameFromToken(token);
        assertEquals("testUser", username);
    }
}