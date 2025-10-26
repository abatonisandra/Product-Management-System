package com.example.productmanagement.repository;

import com.example.productmanagement.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing {@link Category} entities.  Extends
 * {@link JpaRepository} to provide CRUD operations and paging support.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Additional query methods can be declared here if needed.
}