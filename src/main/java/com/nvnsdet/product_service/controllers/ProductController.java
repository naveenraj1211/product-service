package com.nvnsdet.product_service.controllers;


import com.nvnsdet.product_service.dtos.CategoryDto;
import com.nvnsdet.product_service.dtos.ProductDto;
import com.nvnsdet.product_service.models.Category;
import com.nvnsdet.product_service.models.Product;
import com.nvnsdet.product_service.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    @Qualifier("sps") // or "fkps" for FakeStoreProductService)
    private IProductService productService;

    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Product ID must be greater than 0");
        }

        Product product = productService.getProductById(id);
        if (product != null)
            return product.toProductDto();
        return null;
    }

    @GetMapping("/products")
    public List<ProductDto> getAllProducts()
    {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<>();
        if(products != null && !products.isEmpty())
        {
            for (Product product : products) {
                productDtos.add(product.toProductDto());
            }
        }

        return productDtos;
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto)
    {
        Product product = productDto.toProduct();
        product = productService.createProduct(product);
        if(product != null)
            return product.toProductDto();
        return null;
    }

    @PutMapping("/products/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto)
    {
        Product product = productDto.toProduct();
        product = productService.replaceProduct(product, id);
        if(product != null)
            return product.toProductDto();
        return null;
    }

    @DeleteMapping("/products/{id}")
    public Boolean deleteProductById(@PathVariable Long id)
    {
        Product product = productService.getProductById(id);
        if(product != null) {
           return productService.deleteProduct(id);
        }
        return false;
    }


}
