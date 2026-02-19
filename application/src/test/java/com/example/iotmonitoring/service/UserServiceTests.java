package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.User;
import com.example.iotmonitoring.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the UserService class.
 */
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername("testuser");
        user.setPassword("password");
    }

    /**
     * Test for saving a user.
     */
    @Test
    public void testSaveUser() {
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.saveUser(user);
        assertEquals(user.getId(), savedUser.getId());
        verify(userRepository, times(1)).save(user);
    }
}