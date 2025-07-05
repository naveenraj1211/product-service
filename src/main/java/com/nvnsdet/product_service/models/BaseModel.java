package com.nvnsdet.product_service.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {

    @Id
    private Long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;

    private State state;


}
