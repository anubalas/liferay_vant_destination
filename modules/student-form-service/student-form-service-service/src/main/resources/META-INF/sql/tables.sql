import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import java.util.List;

package com.example.rest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Student_Student")
public class Student {
    
    @Id
    @Column(nullable = false)
    private Long studentId;

    @Column(length = 75)
    private String name;

    @Column(length = 75)
    private String email;
}