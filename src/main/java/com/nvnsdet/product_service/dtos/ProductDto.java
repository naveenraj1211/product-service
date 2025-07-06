package com.nvnsdet.product_service.dtos;


import com.nvnsdet.product_service.models.Category;
import com.nvnsdet.product_service.models.Product;
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

    public Product toProduct()
    {
        Product product = new Product();
        product.setId(this.getId());
        product.setName(this.getName());
        product.setDescription(this.getDescription());
        product.setPrice(this.getPrice());
        product.setImageUrl(this.getImageUrl());
        if(this.getCategory() != null)
        {
            Category category = new Category();
            category.setId(this.getCategory().getId());
            category.setName(this.getCategory().getName());
            category.setDescription(this.getCategory().getDescription());

            product.setCategory(category);
        }

        return product;
    }
}
