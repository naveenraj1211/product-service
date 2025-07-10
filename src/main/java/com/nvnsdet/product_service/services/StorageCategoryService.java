package com.nvnsdet.product_service.services;

import com.nvnsdet.product_service.exceptions.CategoryNotFoundException;
import com.nvnsdet.product_service.models.Category;
import com.nvnsdet.Category_service.services.ICategoryService;
import com.nvnsdet.product_service.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageCategoryService implements ICategoryService {


    private final CategoryRepo categoryRepo;

    @Autowired
    public StorageCategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> categoryOptional = categoryRepo.findById(id);
        return categoryOptional.orElseThrow(()->
                new CategoryNotFoundException("Category with ID " + id + " does not exist."));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        if (category.getId() != null && categoryRepo.existsById(category.getId())) {
            throw new IllegalArgumentException("Category with ID " + category.getId() + " already exists.");
        }
        return categoryRepo.save(category);
    }

    @Override
    public Category replaceCategory(Category category, Long id) {
       return categoryRepo.findById(id).map(existingCategory ->{
            category.setId(id);
            return categoryRepo.save(category);
        }).orElseThrow(() -> new CategoryNotFoundException("Category not found with ID " + id));
    }

    @Override
    public void deleteCategory(Long id) {
        if(!categoryRepo.existsById(id)){
            throw new CategoryNotFoundException("Category not found with ID: " + id);
        }
        categoryRepo.deleteById(id);
    }
}
