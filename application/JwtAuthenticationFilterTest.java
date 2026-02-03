import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JwtAuthenticationFilterTest tests the functionality of the JwtAuthenticationFilter class,
 * validating the JWT token processing.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class JwtAuthenticationFilterTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test for valid token processing.
     */
    @WithMockUser
    @Test
    public void testDoFilterInternalWithValidToken() throws Exception {
        String token = "validToken";
        when(jwtTokenProvider.validateToken(token)).thenReturn(true);
        when(jwtTokenProvider.getUsernameFromToken(token)).thenReturn("testUser");

        mockMvc.perform(get("/api/devices/").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    /**
     * Test for invalid token processing.
     */
    @WithMockUser
    @Test
    public void testDoFilterInternalWithInvalidToken() throws Exception {
        String token = "invalidToken";
        when(jwtTokenProvider.validateToken(token)).thenReturn(false);

        mockMvc.perform(get("/api/devices/").header("Authorization", "Bearer " + token))
                .andExpect(status().isUnauthorized());
    }
}