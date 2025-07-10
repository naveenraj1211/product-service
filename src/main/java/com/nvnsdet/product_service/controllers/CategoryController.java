package com.nvnsdet.product_service.controllers;


import com.nvnsdet.product_service.dtos.CategoryDto;
import com.nvnsdet.product_service.models.Category;
import com.nvnsdet.Category_service.services.ICategoryService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/categories")
@Validated  // 👈 enables method parameter validation
public class CategoryController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        // Constructor can be used for dependency injection if needed
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable @Min(1) Long id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category.toCategoryDto());
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDto> categoryDtos = categories.stream()
                .map(Category::toCategoryDto)
                .toList();
        return ResponseEntity.ok(categoryDtos);
    }


    @PostMapping()
    public ResponseEntity<CategoryDto> createCategoryById(@RequestBody CategoryDto categoryDto) {
        Category category = categoryDto.toCategory();
        category = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category.toCategoryDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategoryById(@PathVariable @Min(1) Long id, @RequestBody CategoryDto categoryDto) {
        Category category = categoryDto.toCategory();
        category = categoryService.replaceCategory(category,id);
        return ResponseEntity.ok(category.toCategoryDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable @Min(1) Long id) {
       categoryService.deleteCategory(id);
       return ResponseEntity.ok("Category deleted successfully");
    }

}
