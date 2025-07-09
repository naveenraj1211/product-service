package com.nvnsdet.product_service.services;

import com.nvnsdet.product_service.models.Product;
import com.nvnsdet.product_service.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("sps")
public class StorageProductService implements IProductService{

    @Autowired
    ProductRepo productRepo;

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product createProduct(Product product) {

        Optional<Product> productOptional = productRepo.findById(product.getId());
        //throw new IllegalArgumentException("Product with ID " + product.getId() + " already exists.");
        return productOptional.orElseGet(() -> productRepo.save(product));
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        return productRepo.save(product);
    }

    @Override
    public Boolean deleteProduct(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            productRepo.deleteById(id);
        }
        return false;
    }
}
