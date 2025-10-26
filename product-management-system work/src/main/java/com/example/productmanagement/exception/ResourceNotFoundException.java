package com.example.productmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when a requested resource (such as a product or category) cannot be
 * located.  By annotating the class with {@code @ResponseStatus}, Spring
 * automatically maps the exception to a 404 Not Found HTTP status when it
 * propagates from a controller.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}