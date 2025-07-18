package com.nvnsdet.product_service.controllers;

import com.nvnsdet.product_service.dtos.ProductDto;
import com.nvnsdet.product_service.exceptions.ProductNotFoundException;
import com.nvnsdet.product_service.models.Product;
import com.nvnsdet.product_service.services.IProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private IProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void TestProductDetailsById_WithValidProductId_ShouldReturnProductDetails() {
        // Arrange
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setName("Test Product");
        product.setPrice(100000D);
        when(productService.getProductById(id)).thenReturn(product);

        // Act
        ResponseEntity<ProductDto> response = productController.getProductById(id);
        ProductDto productDto = response.getBody();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(productDto);
        assertEquals(id, productDto.getId());
        assertEquals("Test Product", productDto.getName());
        assertEquals(100000D, productDto.getPrice());
    }

    @Test
    public void TestGetProductById_WithInvalidProductId_ShouldReturnNotFoundException() {
        // Arrange
        Long id = -2L;
        when(productService.getProductById(id)).thenThrow(new ProductNotFoundException("Product with ID " + id + " does not exist."));
        // Act
        Exception exception = assertThrows(ProductNotFoundException.class, () -> productController.getProductById(id));

        // Assert
        assertEquals("Product with ID " + id + " does not exist.", exception.getMessage());
    }

}