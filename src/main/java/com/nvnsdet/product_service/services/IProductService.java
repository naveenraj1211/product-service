package com.nvnsdet.product_service.services;

import com.nvnsdet.product_service.models.Product;

import java.util.List;

public interface IProductService {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product replaceProduct(Product product, Long id);

    Boolean deleteProduct(Long id);
}
