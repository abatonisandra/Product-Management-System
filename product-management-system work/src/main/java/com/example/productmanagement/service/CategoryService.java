package com.example.productmanagement.service;

import com.example.productmanagement.entity.Category;
import com.example.productmanagement.exception.ResourceNotFoundException;
import com.example.productmanagement.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

   
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

  
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    
    public Category update(Long id, Category details) {
        Category category = findById(id);
        category.setName(details.getName());
        return categoryRepository.save(category);
    }

    
    public void delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }
}
