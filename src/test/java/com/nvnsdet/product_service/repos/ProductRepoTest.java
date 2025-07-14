package com.nvnsdet.product_service.repos;

import com.nvnsdet.product_service.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductRepoTest {

    @Autowired
    ProductRepo productRepo;

    @Test
    public void testQuery(){
       // List<Product> products = productRepo.findProductOrderByPriceDesc();
        List<Product> products = productRepo.findProductByOrderByPriceDesc();
        for (Product product : products) {
            System.out.println(product.getName() + " - " + product.getPrice());
        }
    }
}
