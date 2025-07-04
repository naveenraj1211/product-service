package com.nvnsdet.product_service.controllers;


import com.nvnsdet.product_service.dtos.CategoryDto;
import com.nvnsdet.product_service.dtos.ProductDto;
import com.nvnsdet.product_service.models.Category;
import com.nvnsdet.product_service.models.Product;
import com.nvnsdet.product_service.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable Long id)
    {
        Product product = productService.getProductById(id);
        if(product != null)
            return from(product);
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
                productDtos.add(from(product));
            }
        }

        return productDtos;
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto)
    {
        Product product = from(productDto);
        product = productService.createProduct(product);
        if(product != null)
            return from(product);
        return null;
    }

    @PutMapping("/products/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto)
    {
        Product product = from(productDto);
        product = productService.replaceProduct(product, id);
        if(product != null)
            return from(product);
        return null;
    }

    @DeleteMapping("/products/{id}")
    public ProductDto deleteProductById(@PathVariable Long id)
    {
        Product product = productService.deleteProduct(id);
        if(product != null)
            return from(product);
        return null;
    }

    public ProductDto from(Product product)
    {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null)
        {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setDescription(product.getCategory().getDescription());

            productDto.setCategory(categoryDto);
        }
        return productDto;
    }

    public Product from(ProductDto productDto)
    {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        if(productDto.getCategory() != null)
        {
            Category category = new Category();
            category.setId(productDto.getCategory().getId());
            category.setName(productDto.getCategory().getName());
            category.setDescription(productDto.getCategory().getDescription());

            product.setCategory(category);
        }

        return product;
    }
}
