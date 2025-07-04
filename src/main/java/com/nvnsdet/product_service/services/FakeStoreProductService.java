package com.nvnsdet.product_service.services;


import com.nvnsdet.product_service.dtos.FakeStoreProductDto;
import com.nvnsdet.product_service.dtos.ProductDto;
import com.nvnsdet.product_service.models.Category;
import com.nvnsdet.product_service.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service // This annotation indicates that this class is a service component in the Spring context.
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductById(Long id) {
        // This method will be implemented to fetch a product by its ID from the Fake Store API.
        // The implementation will use the RestTemplateBuilder to create a RestTemplate instance.
        // The actual logic for fetching the product will be added later.

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity  =
                restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, id);

        if(fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful() && fakeStoreProductDtoResponseEntity.getBody() != null) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;
    }


    @Override
    public List<Product> getAllProducts() {
        // This method will be implemented to fetch a product by its ID from the Fake Store API.
        // The implementation will use the RestTemplateBuilder to create a RestTemplate instance.
        // The actual logic for fetching the product will be added later.

        List<Product> products = new ArrayList<>();
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoResponseEntity  =
                restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        if(fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful() && fakeStoreProductDtoResponseEntity.getBody() != null) {

            for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtoResponseEntity.getBody()) {
                products.add(from(fakeStoreProductDto));
            }
            return products;
        }
        return products;
    }

    public Product from(FakeStoreProductDto fakeStoreProductDto) {
        // This method converts a FakeStoreProductDto to a Product entity.
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        if(fakeStoreProductDto.getCategory() != null)
        {
            Category category = new Category();
            category.setName(fakeStoreProductDto.getCategory());
            product.setCategory(category);
        }

        return product;
    }


}
