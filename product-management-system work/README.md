# Product Management System

This repository contains a sample **E‑Commerce Product Management System** built
with [Spring Boot](https://spring.io/projects/spring-boot) and Spring Data
JPA.  The goal of this project is to demonstrate how to develop a robust
backend for managing products and categories while leveraging Spring Boot’s
auto‑configuration and data binding capabilities.

## Features

* **Spring Boot setup** – The project is configured as a Spring Boot
  application using the [`spring-boot-starter-web`](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#web)
  and `spring-boot-starter-data-jpa` starters.  Spring Boot’s auto‑configuration
  registers the `DispatcherServlet` so there is no need to define it manually
  in `web.xml`【686759059066949†L112-L120】.
* **Request handling** – REST controllers use `@RestController`, `@GetMapping`,
  `@PostMapping` and related annotations to map HTTP requests.  Path variables
  and query parameters are automatically bound to method arguments with
  appropriate type conversion【314475834642519†L245-L248】.  Responses are
  returned via `ResponseEntity` with appropriate status codes.  A global
  exception handler ensures consistent error responses.
* **Data binding** – Request bodies are bound to Java objects using
  `@RequestBody` and validated using the Bean Validation API (see
  `GlobalExceptionHandler`).  Spring automatically converts parameter values
  (e.g. from strings to `Long`, `Integer` or `Double`) during binding【314475834642519†L245-L248】.
* **Spring Data JPA** – Entities `Category` and `Product` map to relational
  tables.  Repository interfaces extend `JpaRepository` to provide CRUD
  operations, while custom JPQL and native queries enable advanced searches.
* **H2 database** – For ease of development, an in‑memory H2 database is
  configured via `application.properties`.  This can be replaced with
  another database by changing the datasource properties.

## Building and running

To build and run the application locally, you will need **Java 21** and
**Maven** installed.

```bash
# Clone the repository and change directory
git clone <your-fork-url>
cd product-management-system

# Build the project and run tests (if any)
mvn clean package

# Run the application
mvn spring-boot:run

# Alternatively run the packaged jar
java -jar target/product-management-system-0.0.1-SNAPSHOT.jar
```

Once started, the API will be available at `http://localhost:8080`.  You can
access the H2 console at `http://localhost:8080/h2-console` (leave username
empty and password as default).  Swagger/OpenAPI documentation can be added
easily if desired.

## API overview

### Categories

- `GET /api/categories` – list all categories
- `GET /api/categories/{id}` – get details for a category
- `POST /api/categories` – create a new category
- `PUT /api/categories/{id}` – update a category
- `DELETE /api/categories/{id}` – delete a category

### Products

- `GET /api/products` – list all products
- `GET /api/products/{id}` – get details for a product
- `POST /api/products/category/{categoryId}` – create a new product in a category
- `PUT /api/products/{id}` – update a product
- `DELETE /api/products/{id}` – delete a product
- `GET /api/products/search?name=...` – search products by name (case insensitive)
- `GET /api/products/price-range?min=...&max=...` – search products in a price range
- `GET /api/products/low-stock?threshold=...` – find products with stock below a threshold

## Notes

* This project is intended to illustrate concepts rather than serve as a
  production‑ready system.  For real‑world use, consider adding input
  validation, authentication/authorization (e.g. Spring Security), pagination
  and sorting, DTO classes, and comprehensive testing.
* Spring Boot automatically configures the `DispatcherServlet` and the
  conversion service.  The controllers demonstrate how to leverage
  Spring’s data binding features via `@PathVariable`, `@RequestParam` and
  `@RequestBody` without manually configuring a servlet【686759059066949†L112-L120】【314475834642519†L245-L248】.

Happy coding!