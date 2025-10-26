package com.example.productmanagement.service;

import com.example.productmanagement.entity.Category;
import com.example.productmanagement.entity.Product;
import com.example.productmanagement.exception.ResourceNotFoundException;
import com.example.productmanagement.repository.CategoryRepository;
import com.example.productmanagement.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service layer for {@link Product} operations.  Encapsulates business logic
 * including association with categories and query functionality.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Retrieves all products.
     *
     * @return list of products
     */
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a product by its id.
     *
     * @param id product id
     * @return the product
     */
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    /**
     * Creates a new product under the specified category.
     *
     * @param categoryId the id of the category to attach the product to
     * @param product    product details
     * @return the created product
     */
    public Product create(Long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
        product.setCategory(category);
        return productRepository.save(product);
    }

    /**
     * Updates an existing product.  If a category id is present on the
     * provided product, the association will be updated accordingly.
     *
     * @param id      product id
     * @param details new product values
     * @return the updated product
     */
    public Product update(Long id, Product details) {
        Product existing = findById(id);
        existing.setName(details.getName());
        existing.setDescription(details.getDescription());
        existing.setPrice(details.getPrice());
        existing.setQuantity(details.getQuantity());
        // Update category if supplied
        if (details.getCategory() != null && details.getCategory().getId() != null) {
            Category category = categoryRepository.findById(details.getCategory().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + details.getCategory().getId()));
            existing.setCategory(category);
        }
        return productRepository.save(existing);
    }

    /**
     * Deletes a product by id.
     *
     * @param id product id
     */
    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

    /**
     * Searches for products by case‑insensitive name fragment.
     *
     * @param name name fragment
     * @return matching products
     */
    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Searches for products within a price range.
     *
     * @param min minimum price
     * @param max maximum price
     * @return products in the range
     */
    public List<Product> searchByPriceRange(Double min, Double max) {
        return productRepository.findByPriceRange(min, max);
    }

    /**
     * Finds products whose stock quantity is below a threshold.
     *
     * @param threshold the stock threshold
     * @return products with low stock
     */
    public List<Product> findLowStock(Integer threshold) {
        return productRepository.findProductsWithLowStock(threshold);
    }
}