package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.Role;
import com.example.iotmonitoring.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RoleService provides role-related operations.
 */
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Saves a new role to the database.
     * @param role the role to save
     * @return the saved role
     */
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}