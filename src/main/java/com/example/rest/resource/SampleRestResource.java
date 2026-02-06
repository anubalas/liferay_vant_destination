package com.example.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class SampleRestResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HelloResponse sayHello() {
        return new HelloResponse("Hello from Liferay REST API using Java 11!");
    }

    // Minimal POJO inside the same file
    public static class HelloResponse {
        public String message;

        public HelloResponse(String message) {
            this.message = message;
        }
    }
}
