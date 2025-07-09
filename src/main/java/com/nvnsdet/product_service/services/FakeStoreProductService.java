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

@Service("fkps")
// This annotation indicates that this class is a service component in the Spring context.
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity  =
                restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, id);
        if(fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful() && fakeStoreProductDtoResponseEntity.getBody() != null) {
            return fakeStoreProductDtoResponseEntity.getBody().toProduct();
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
                products.add(fakeStoreProductDto.toProduct());
            }
            return products;
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = product.toFakeStoreProductDto();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                callForEntity("https://fakestoreapi.com/products", HttpMethod.POST, fakeStoreProductDto,
                        FakeStoreProductDto.class);

        if(fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful() && fakeStoreProductDtoResponseEntity.getBody() != null) {
            return fakeStoreProductDtoResponseEntity.getBody().toProduct();
        }
        return null;
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        FakeStoreProductDto fakeStoreProductDto = product.toFakeStoreProductDto();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                callForEntity("https://fakestoreapi.com/products/{id}", HttpMethod.PUT, fakeStoreProductDto,
                        FakeStoreProductDto.class, id);

        if(fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful() && fakeStoreProductDtoResponseEntity.getBody() != null) {
            return fakeStoreProductDtoResponseEntity.getBody().toProduct();
        }
        return null;
    }

    @Override
    public Boolean deleteProduct(Long id) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                callForEntity("https://fakestoreapi.com/products/{id}", HttpMethod.DELETE,null,
                       FakeStoreProductDto.class, id);

        if(fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful() && fakeStoreProductDtoResponseEntity.getBody() != null) {
            // fakeStoreProductDtoResponseEntity.getBody().toProduct();
            return true;
        }
        return false;
    }


    public <T> ResponseEntity<T> callForEntity(String url,HttpMethod httpMethod,  @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }




}
