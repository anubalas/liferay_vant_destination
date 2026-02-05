package com.example.rest.application;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;
import java.util.Set;
import java.util.HashSet;

import com.example.rest.resource.SampleRestResource;

@ApplicationPath("/sample")
public class SampleRestApplication extends Application {

// adding comments 

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(SampleRestResource.class);
        return classes;
    }
}
