package com.example.productmanagement.repository;

import com.example.productmanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    
    List<Product> findByNameContainingIgnoreCase(String name);

    
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
    List<Product> findByPriceRange(@Param("min") Double min, @Param("max") Double max);

    
    @Query(value = "SELECT * FROM products WHERE quantity < :threshold", nativeQuery = true)
    List<Product> findProductsWithLowStock(@Param("threshold") Integer threshold);
}
