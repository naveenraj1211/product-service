package com.nvnsdet.product_service.services;


import com.nvnsdet.product_service.dtos.FakeStoreProductDto;
import com.nvnsdet.product_service.models.Category;
import com.nvnsdet.product_service.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service // This annotation indicates that this class is a service component in the Spring context.
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductById(Long id) {
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

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = from(product);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                callForEntity("https://fakestoreapi.com/products", HttpMethod.POST, fakeStoreProductDto,
                        FakeStoreProductDto.class);

        if(fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful() && fakeStoreProductDtoResponseEntity.getBody() != null) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        FakeStoreProductDto fakeStoreProductDto = from(product);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                callForEntity("https://fakestoreapi.com/products/{id}", HttpMethod.PUT, fakeStoreProductDto,
                        FakeStoreProductDto.class, id);

        if(fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful() && fakeStoreProductDtoResponseEntity.getBody() != null) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                callForEntity("https://fakestoreapi.com/products/{id}", HttpMethod.DELETE,null,
                       FakeStoreProductDto.class, id);

        if(fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful() && fakeStoreProductDtoResponseEntity.getBody() != null) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;
    }


    public <T> ResponseEntity<T> callForEntity(String url,HttpMethod httpMethod,  @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }


    public Product from(FakeStoreProductDto fakeStoreProductDto) {
        // This method converts a FakeStoreProductDto to a Product entity.
        Product product = new Product();
        if(fakeStoreProductDto.getId() != null)
            product.setId(fakeStoreProductDto.getId());


        if(fakeStoreProductDto.getTitle() != null)
            product.setName(fakeStoreProductDto.getTitle());
        if(fakeStoreProductDto.getDescription() != null)
            product.setDescription(fakeStoreProductDto.getDescription());
        if(fakeStoreProductDto.getPrice() != null)
            product.setPrice(fakeStoreProductDto.getPrice());
        if(fakeStoreProductDto.getImage() != null)
            product.setImageUrl(fakeStoreProductDto.getImage());
        if(fakeStoreProductDto.getCategory() != null)
        {
            Category category = new Category();
            category.setName(fakeStoreProductDto.getCategory());
            product.setCategory(category);
        }
        return product;
    }

    public FakeStoreProductDto from(Product product) {
        // This method converts a Product entity to a FakeStoreProductDto.
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        if(product.getId() != null)
            fakeStoreProductDto.setId(product.getId());
        if(product.getName() != null)
            fakeStoreProductDto.setTitle(product.getName());
        if(product.getDescription() != null)
            fakeStoreProductDto.setDescription(product.getDescription());
        if (product.getPrice() != null)
            fakeStoreProductDto.setPrice(product.getPrice());
        if(product.getImageUrl() != null)
            fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null)
        {
           fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }

}
