package com.example.productmanagement.repository;

import com.example.productmanagement.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
   
}
