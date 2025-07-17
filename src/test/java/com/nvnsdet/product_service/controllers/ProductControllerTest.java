package com.nvnsdet.product_service.controllers;

import com.nvnsdet.product_service.dtos.ProductDto;
import com.nvnsdet.product_service.models.Product;
import com.nvnsdet.product_service.services.IProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
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
        Long productId = 1L;
        Product mockProduct = new Product();
        mockProduct.setId(productId);
        mockProduct.setName("Test Product");

        when(productService.getProductById(productId)).thenReturn(mockProduct);

        // Act
        ResponseEntity<ProductDto> response = productController.getProductById(productId);
        ProductDto productDto = response.getBody();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(productDto);
        assertEquals(productId, productDto.getId());
        assertEquals("Test Product", productDto.getName());
    }

}