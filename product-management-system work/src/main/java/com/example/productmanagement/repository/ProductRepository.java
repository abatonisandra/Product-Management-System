package com.example.productmanagement.repository;

import com.example.productmanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Repository for {@link Product} entities.  In addition to the default CRUD
 * methods provided by {@link JpaRepository}, this interface defines
 * additional query methods using JPQL and native SQL.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds products whose names contain the given fragment, case‑insensitive.
     *
     * @param name the name fragment
     * @return a list of matching products
     */
    List<Product> findByNameContainingIgnoreCase(String name);

    /**
     * Finds products whose price falls within the specified range.  This JPQL
     * query demonstrates how to use @Query with positional parameters.
     *
     * @param min minimum price (inclusive)
     * @param max maximum price (inclusive)
     * @return products within the price range
     */
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
    List<Product> findByPriceRange(@Param("min") Double min, @Param("max") Double max);

    /**
     * Finds products whose stock (quantity) falls below a given threshold.  The
     * query is written in native SQL to illustrate the ability to execute
     * database‑specific queries when needed.
     *
     * @param threshold the quantity threshold
     * @return products with quantity less than the threshold
     */
    @Query(value = "SELECT * FROM products WHERE quantity < :threshold", nativeQuery = true)
    List<Product> findProductsWithLowStock(@Param("threshold") Integer threshold);
}