package com.nvnsdet.product_service.models;

import com.nvnsdet.product_service.dtos.CategoryDto;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {

    private String name;
    private String description;
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Product> products;


    public Category() {
        // Default constructor
        this.setCreatedAt(new Date());
        this.setUpdatedAt(new Date());
        this.setState(State.ACTIVE);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", products=" + products +
                '}';
    }

    public CategoryDto toCategoryDto() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(this.getId());
        categoryDto.setName(this.getName());
        categoryDto.setDescription(this.getDescription());
        return categoryDto;
    }


}
