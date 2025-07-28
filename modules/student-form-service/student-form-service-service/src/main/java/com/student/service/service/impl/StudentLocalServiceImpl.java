package com.student.service.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.stereotype.Component; 
import com.example.rest.AopService; 
import com.example.rest.StudentLocalServiceBaseImpl; 
import java.util.List; 
import java.util.Map; 
import java.util.Optional; 
import java.util.stream.Collectors; 
import java.util.stream.Stream;

package com.student.service.impl;

import org.springframework.stereotype.Component;

@Component(
    property = "model.class.name=com.student.service.model.Student",
    service = AopService.class
)
public class StudentLocalServiceImpl extends StudentLocalServiceBaseImpl {
}