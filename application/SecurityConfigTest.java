import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * SecurityConfigTest tests the security configuration of the application,
 * validating JWT-based authentication and role-based authorization.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test for public access to authentication endpoints.
     */
    @Test
    public void testPublicAccessToAuthEndpoints() throws Exception {
        mockMvc.perform(get("/api/auth/login"))
                .andExpect(status().isOk());
    }

    /**
     * Test for access to device endpoints with USER role.
     */
    @WithMockUser(roles = "USER")
    @Test
    public void testUserAccessToDeviceEndpoints() throws Exception {
        mockMvc.perform(get("/api/devices/"))
                .andExpect(status().isOk());
    }

    /**
     * Test for access to admin endpoints with ADMIN role.
     */
    @WithMockUser(roles = "ADMIN")
    @Test
    public void testAdminAccessToAdminEndpoints() throws Exception {
        mockMvc.perform(get("/api/admin/"))
                .andExpect(status().isOk());
    }

    /**
     * Test for access denied for unauthorized users.
     */
    @Test
    public void testAccessDeniedForUnauthorizedUsers() throws Exception {
        mockMvc.perform(get("/api/devices/"))
                .andExpect(status().isUnauthorized());
    }
}