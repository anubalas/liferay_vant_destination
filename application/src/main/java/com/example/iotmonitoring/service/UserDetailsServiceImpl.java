package com.example.iotmonitoring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * UserDetailsServiceImpl implements UserDetailsService to load user-specific data.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Map<String, String> users = new HashMap<>(); // In-memory user store

    @Autowired
    public UserDetailsServiceImpl() {
        // Sample user data
        users.put("user", "password"); // Password should be hashed in a real application
    }

    /**
     * Load user by username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = users.get(username);
        if (password == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User.withUsername(username)
                .password(password)
                .roles("USER")
                .build();
    }
}