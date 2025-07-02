package com.nvnsdet.product_service.models;

import java.util.Date;

public abstract class BaseModel {

    private String id;
    private String name;
    private Date createdAt;
    private Date updatedAt;

    private State state;


}
