package com.nvnsdet.product_service.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{

    private String description;
    private Double price;
    private String imageUrl;
    private Category category;
    private boolean isPrime;

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
}
