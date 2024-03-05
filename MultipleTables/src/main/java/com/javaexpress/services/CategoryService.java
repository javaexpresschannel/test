package com.javaexpress.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.entities.Category;
import com.javaexpress.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
	
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
    }

    public Category createCategory(Category category) {
        // Add additional business logic if needed
        return categoryRepository.save(category);
    }

    public void updateCategory(Long id, Category updatedCategory) {
        Category existingCategory = getCategoryById(id);
        // Update the fields of existingCategory with the fields of updatedCategory
        existingCategory.setName(updatedCategory.getName());
        // Add additional business logic if needed
        categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id) {
        Category existingCategory = getCategoryById(id);
        // Add additional business logic if needed
        categoryRepository.delete(existingCategory);
    }
}

