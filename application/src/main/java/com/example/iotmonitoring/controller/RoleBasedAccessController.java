package com.example.iotmonitoring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to demonstrate role-based access control in the IoT Monitoring Microservice.
 */
@RestController
@RequestMapping("/api")
public class RoleBasedAccessController {
    @GetMapping("/admin/data")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminData() {
        return "Admin data accessed";
    }

    @GetMapping("/user/data")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String userData() {
        return "User data accessed";
    }

    @GetMapping("/viewer/data")
    @PreAuthorize("hasAnyRole('VIEWER', 'USER', 'ADMIN')")
    public String viewerData() {
        return "Viewer data accessed";
    }
}