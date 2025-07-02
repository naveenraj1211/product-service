package com.nvnsdet.product_service.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseModel {

    private Long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;

    private State state;


}
