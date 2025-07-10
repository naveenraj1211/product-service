package com.nvnsdet.product_service.services;

import com.nvnsdet.product_service.exceptions.ProductNotFoundException;
import com.nvnsdet.product_service.models.Product;
import com.nvnsdet.product_service.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("sps")
public class StorageProductService implements IProductService {


    ProductRepo productRepo;

    @Autowired
    public StorageProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product with ID " + id + " does not exist."));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product createProduct(Product product) {

        if (product.getId() != null && productRepo.existsById(product.getId())) {
            throw new IllegalArgumentException("Product with ID " + product.getId() + " already exists.");
        }
        return productRepo.save(product);
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        return productRepo.findById(id)
                .map(existingProduct -> {
                    product.setId(id); // Ensure the ID is set for the update
                    return productRepo.save(product);
                })
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " does not exist."));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepo.existsById(id)) {
            throw new ProductNotFoundException("Product not found with ID: " + id);
        }
        productRepo.deleteById(id);
    }
}
