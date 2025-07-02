package com.nvnsdet.product_service.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {


    private Long id;
    private String name;
    private String description;
    private CategoryDto categoryDto;
    private Double price;



}
