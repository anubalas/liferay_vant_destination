package com.example.iotmonitoring.service;

import com.example.iotmonitoring.model.User;
import com.example.iotmonitoring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService provides user-related operations.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Saves a new user to the database.
     * @param user the user to save
     * @return the saved user
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}