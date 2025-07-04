package com.nvnsdet.product_service.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {


    private Long id;
    private String name;
    private String description;
    private CategoryDto category;
    private Double price;
    private String imageUrl;

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
