package com.nvnsdet.product_service.controllers;


import com.nvnsdet.product_service.dtos.CategoryDto;
import com.nvnsdet.product_service.models.Category;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {


    @GetMapping("/categories/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id){
        return new CategoryDto();
    }

    @PostMapping("/categories")
    public CategoryDto createCategoryById(@RequestBody CategoryDto categoryDto){
        return new CategoryDto();
    }

    @PutMapping("/categories/{id}")
    public CategoryDto updateCategoryById(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        return new CategoryDto();
    }

    @DeleteMapping("/categories/{id}")
    public CategoryDto deleteCategoryById(@PathVariable Long id){
        return new CategoryDto();
    }


    public CategoryDto from(Category category)
    {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryDto.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());

        return categoryDto;
    }


    public Category from(CategoryDto categoryDto)
    {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }
}
