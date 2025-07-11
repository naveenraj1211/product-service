package com.nvnsdet.product_service.services;

import com.nvnsdet.product_service.exceptions.ProductNotFoundException;
import com.nvnsdet.product_service.models.Category;
import com.nvnsdet.product_service.models.Product;
import com.nvnsdet.product_service.repos.CategoryRepo;
import com.nvnsdet.product_service.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("sps")
public class StorageProductService implements IProductService {


    ProductRepo productRepo;
    CategoryRepo categoryRepo;


    @Autowired
    public StorageProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
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
        Category category = product.getCategory();
        if (category != null && category.getId() != null)
        {
            Category persistedCategory = categoryRepo.findById(category.getId())
                    .orElseGet(() -> categoryRepo.save(category));
            product.setCategory(persistedCategory);
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
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " does not exist."));
        Category category = product.getCategory();
        productRepo.delete(product);

        if(category != null && productRepo.countByCategoryId(category.getId()) ==0) {
            categoryRepo.delete(category);
        }

    }
}
