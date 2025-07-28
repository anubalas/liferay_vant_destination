package com.student.service.exception;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

package com.example.rest;

public class NoSuchStudentException extends NoSuchModelException {

    public NoSuchStudentException(String message, Throwable cause) {
        super(message, cause);
    }

    package com.example.rest;

    public class NoSuchStudentException extends Exception {
        
        public NoSuchStudentException() {
            super();
        }

        // This class represents an exception that is thrown when a student cannot be found in the system.
        // It should be used in scenarios where a search operation fails to locate a student in the database.
    }

    package com.example.rest;

    public class NoSuchStudentException extends Exception {
        // Constructor for NoSuchStudentException
        public NoSuchStudentException(String msg) {
            super(msg); // Initialize the exception message using the superclass constructor
        }
    }

    package com.example.rest;

    public class NoSuchStudentException extends Exception {

        /**
         * Constructs a new NoSuchStudentException with the specified detail message and cause.
         *
         * @param msg       the detail message
         * @param throwable the cause of the exception
         */
        public NoSuchStudentException(String msg, Throwable throwable) {
            super(msg, throwable);
        }
    }

    package com.example.rest;

    public class NoSuchStudentException extends Exception {

        public NoSuchStudentException(Throwable throwable) {
            super(throwable);
        }
    }
}