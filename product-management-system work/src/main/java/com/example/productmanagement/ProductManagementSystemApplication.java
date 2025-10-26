package com.example.productmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the E‑Commerce Product Management System.  The
 * {@code @SpringBootApplication} annotation triggers component scanning,
 * auto‑configuration and property support.  Spring Boot automatically
 * registers the DispatcherServlet and configures it to map requests to
 * controller methods without any manual servlet configuration【686759059066949†L112-L120】.
 */
@SpringBootApplication
public class ProductManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductManagementSystemApplication.class, args);
    }
}