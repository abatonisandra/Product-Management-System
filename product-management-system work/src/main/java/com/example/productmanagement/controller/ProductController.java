package com.example.productmanagement.controller;

import com.example.productmanagement.entity.Product;
import com.example.productmanagement.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping("/category/{categoryId}")
    public ResponseEntity<Product> createProduct(@PathVariable Long categoryId, @RequestBody Product product) {
        Product created = productService.create(categoryId, product);
        return ResponseEntity.status(201).body(created);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.update(id, product));
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam(name = "name", required = false, defaultValue = "") String name) {
        return ResponseEntity.ok(productService.searchByName(name));
    }

    
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> searchByPriceRange(@RequestParam("min") Double min, @RequestParam("max") Double max) {
        return ResponseEntity.ok(productService.searchByPriceRange(min, max));
    }

    
    @GetMapping("/low-stock")
    public ResponseEntity<List<Product>> findLowStock(@RequestParam("threshold") Integer threshold) {
        return ResponseEntity.ok(productService.findLowStock(threshold));
    }
}
