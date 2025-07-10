package com.nvnsdet.product_service.models;


import com.nvnsdet.product_service.dtos.CategoryDto;
import com.nvnsdet.product_service.dtos.FakeStoreProductDto;
import com.nvnsdet.product_service.dtos.ProductDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Product extends BaseModel {

    private String description;
    private Double price;
    private String imageUrl;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    private boolean isPrime;


    public Product() {
        // Default constructor
        this.setCreatedAt(new Date());
        this.setUpdatedAt(new Date());
        this.setState(State.ACTIVE);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", category=" + category +
                ", isPrime=" + isPrime +
                '}';
    }


    public FakeStoreProductDto toFakeStoreProductDto() {
        // This method converts a Product entity to a FakeStoreProductDto.
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        if (this.getId() != null)
            fakeStoreProductDto.setId(this.getId());
        if (this.getName() != null)
            fakeStoreProductDto.setTitle(this.getName());
        if (this.getDescription() != null)
            fakeStoreProductDto.setDescription(this.getDescription());
        if (this.getPrice() != null)
            fakeStoreProductDto.setPrice(this.getPrice());
        if (this.getImageUrl() != null)
            fakeStoreProductDto.setImage(this.getImageUrl());
        if (this.getCategory() != null) {
            fakeStoreProductDto.setCategory(this.getCategory().getName());
        }
        return fakeStoreProductDto;
    }

    public ProductDto toProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(this.getId());
        productDto.setName(this.getName());
        productDto.setDescription(this.getDescription());
        productDto.setPrice(this.getPrice());
        productDto.setImageUrl(this.getImageUrl());
        if (this.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(this.getCategory().getId());
            categoryDto.setName(this.getCategory().getName());
            categoryDto.setDescription(this.getCategory().getDescription());

            productDto.setCategory(categoryDto);
        }
        return productDto;
    }

}
