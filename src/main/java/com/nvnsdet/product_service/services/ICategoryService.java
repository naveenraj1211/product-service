package com.nvnsdet.Category_service.services;

import com.nvnsdet.product_service.models.Category;

import java.util.List;

public interface ICategoryService {

    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    Category createCategory(Category category);

    Category replaceCategory(Category category, Long id);

    void deleteCategory(Long id);
    
}
