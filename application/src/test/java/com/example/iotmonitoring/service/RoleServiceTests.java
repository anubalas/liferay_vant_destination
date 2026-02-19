package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.Role;
import com.example.iotmonitoring.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the RoleService class.
 */
public class RoleServiceTests {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    private Role role;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        role = new Role();
        role.setId(UUID.randomUUID());
        role.setName("ROLE_USER");
    }

    /**
     * Test for saving a role.
     */
    @Test
    public void testSaveRole() {
        when(roleRepository.save(role)).thenReturn(role);
        Role savedRole = roleService.saveRole(role);
        assertEquals(role.getId(), savedRole.getId());
        verify(roleRepository, times(1)).save(role);
    }
}