package com.nvnsdet.product_service.controllers;


import com.nvnsdet.product_service.dtos.CategoryDto;
import com.nvnsdet.product_service.dtos.ProductDto;
import com.nvnsdet.product_service.models.Category;
import com.nvnsdet.product_service.models.Product;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {


    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable Long id)
    {
        return new ProductDto();
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto)
    {
        return new ProductDto();
    }

    @PutMapping("/products/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto)
    {
        return new ProductDto();
    }

    @DeleteMapping("/products/{id}")
    public ProductDto deleteProductById(@PathVariable Long id)
    {
        return new ProductDto();
    }

    public ProductDto from(Product product)
    {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        if(product.getCategory() != null)
        {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setDescription(product.getCategory().getDescription());

            productDto.setCategoryDto(categoryDto);
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
        if(productDto.getCategoryDto() != null)
        {
            Category category = new Category();
            category.setId(productDto.getCategoryDto().getId());
            category.setName(productDto.getCategoryDto().getName());
            category.setDescription(productDto.getCategoryDto().getDescription());

            product.setCategory(category);
        }

        return product;
    }
}
