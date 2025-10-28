package com.example.productmanagement.service;

import com.example.productmanagement.entity.Category;
import com.example.productmanagement.entity.Product;
import com.example.productmanagement.exception.ResourceNotFoundException;
import com.example.productmanagement.repository.CategoryRepository;
import com.example.productmanagement.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    
    public Product create(Long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
        product.setCategory(category);
        return productRepository.save(product);
    }

    
    public Product update(Long id, Product details) {
        Product existing = findById(id);
        existing.setName(details.getName());
        existing.setDescription(details.getDescription());
        existing.setPrice(details.getPrice());
        existing.setQuantity(details.getQuantity());
      
        if (details.getCategory() != null && details.getCategory().getId() != null) {
            Category category = categoryRepository.findById(details.getCategory().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + details.getCategory().getId()));
            existing.setCategory(category);
        }
        return productRepository.save(existing);
    }

    
    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

    
    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    
    public List<Product> searchByPriceRange(Double min, Double max) {
        return productRepository.findByPriceRange(min, max);
    }

    
    public List<Product> findLowStock(Integer threshold) {
        return productRepository.findProductsWithLowStock(threshold);
    }
}
