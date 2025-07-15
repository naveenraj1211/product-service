package com.nvnsdet.product_service.repos;

import com.nvnsdet.product_service.models.Category;
import com.nvnsdet.product_service.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryRepoTest {
    @Autowired
    CategoryRepo categoryRepo;

    @Test
    @Transactional
    public void testGetCategory(){
        Category category = categoryRepo.findById(13l).get();
        System.out.println("Category Name: " + category.getName());
        for(Product product : category.getProducts()){
            System.out.println("Product Name: " + product.getName());
        }

    }

    //BatchSize with Select is better than subselect for N+1 Problem
    @Test
    @Transactional
    public void testAllCategoryProducts() {
        List<Category> categoryList = categoryRepo.findAll();
        for(Category category : categoryList) {
            for(Product product : category.getProducts()) {
                System.out.println(product.getName());
            }
        }
    }
}
