package com.nvnsdet.product_service.dtos;


import com.nvnsdet.product_service.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

    private Long id;
    private String name;
    private String description;

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Category toCategory() {
        Category category = new Category();
        category.setId(this.getId());
        category.setName(this.getName());
        category.setDescription(this.getDescription());
        return category;
    }
}
