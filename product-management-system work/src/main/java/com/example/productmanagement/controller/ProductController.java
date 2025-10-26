package com.example.productmanagement.controller;

import com.example.productmanagement.entity.Product;
import com.example.productmanagement.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for product management.  Exposes endpoints to create,
 * retrieve, update and delete products as well as query operations for
 * searching and stock reporting.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves all products.
     */
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    /**
     * Retrieves a product by id using a path variable.  Spring automatically
     * converts the path segment to a {@link Long}【314475834642519†L245-L248】.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    /**
     * Creates a new product within the given category.  Both path variables
     * and request bodies are bound automatically by Spring【314475834642519†L245-L248】.
     */
    @PostMapping("/category/{categoryId}")
    public ResponseEntity<Product> createProduct(@PathVariable Long categoryId, @RequestBody Product product) {
        Product created = productService.create(categoryId, product);
        return ResponseEntity.status(201).body(created);
    }

    /**
     * Updates an existing product.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.update(id, product));
    }

    /**
     * Deletes a product by id.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Searches for products by name using a query parameter.  The name parameter
     * is optional; if omitted, an empty list will be returned.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam(name = "name", required = false, defaultValue = "") String name) {
        return ResponseEntity.ok(productService.searchByName(name));
    }

    /**
     * Retrieves products within a price range.  Both min and max parameters
     * support automatic type conversion to {@link Double}【314475834642519†L245-L248】.
     */
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> searchByPriceRange(@RequestParam("min") Double min, @RequestParam("max") Double max) {
        return ResponseEntity.ok(productService.searchByPriceRange(min, max));
    }

    /**
     * Retrieves products whose stock quantity is below the specified threshold.
     */
    @GetMapping("/low-stock")
    public ResponseEntity<List<Product>> findLowStock(@RequestParam("threshold") Integer threshold) {
        return ResponseEntity.ok(productService.findLowStock(threshold));
    }
}