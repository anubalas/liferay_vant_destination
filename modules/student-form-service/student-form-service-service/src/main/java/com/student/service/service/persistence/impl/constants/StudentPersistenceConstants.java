package com.student.service.service.persistence.impl.constants;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

public class StudentPersistenceConstants {

    public static final String BUNDLE_SYMBOLIC_NAME = "com.student.service.service";

    public static final String ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER = 
        "(origin.bundle.symbolic.name=" + BUNDLE_SYMBOLIC_NAME + ")";

    public static final String SERVICE_CONFIGURATION_FILTER = 
        "(&" + ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER + "(name=service))";
}