package com.nvnsdet.product_service.dtos;

import com.nvnsdet.product_service.models.Category;
import com.nvnsdet.product_service.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {

    private Long id;
    private String title;
    private String description;
    private String category;
    private String image;
    private Double price;

    @Override
    public String toString() {
        return "FakeStoreProductDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }


    public Product toProduct() {
        // This method converts a FakeStoreProductDto to a Product entity.
        Product product = new Product();
        if(this.getId() != null)
            product.setId(this.getId());

        if(this.getTitle() != null)
            product.setName(this.getTitle());
        if(this.getDescription() != null)
            product.setDescription(this.getDescription());
        if(this.getPrice() != null)
            product.setPrice(this.getPrice());
        if(this.getImage() != null)
            product.setImageUrl(this.getImage());
        if(this.getCategory() != null)
        {
            Category category = new Category();
            category.setName(this.getCategory());
            product.setCategory(category);
        }
        return product;
    }

}
