package com.example.iotmonitoring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UserDetailsServiceImplTests validates the UserDetailsServiceImpl functionality.
 */
@SpringBootTest
public class UserDetailsServiceImplTests {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * Test loading user by username.
     */
    @Test
    public void testLoadUserByUsername() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("user");
        assertNotNull(userDetails);
        assertEquals("user", userDetails.getUsername());
    }

    /**
     * Test loading non-existing user.
     */
    @Test
    public void testLoadUserByUsernameNotFound() {
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("nonexistent");
        });
    }
}