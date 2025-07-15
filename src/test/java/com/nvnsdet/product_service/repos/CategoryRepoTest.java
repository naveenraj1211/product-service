package com.nvnsdet.product_service.repos;

import com.nvnsdet.product_service.models.Category;
import com.nvnsdet.product_service.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryRepoTest {
    @Autowired
    CategoryRepo categoryRepo;

    @Test
    public void testGetCategory(){
        Category category = categoryRepo.findById(13l).get();
        System.out.println("Category Name: " + category.getName());
        for(Product product : category.getProducts()){
            System.out.println("Product Name: " + product.getName());
        }

    }
}
