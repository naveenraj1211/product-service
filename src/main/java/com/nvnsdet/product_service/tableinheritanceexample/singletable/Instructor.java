package com.nvnsdet.product_service.tableinheritanceexample.singletable;


import jakarta.persistence.Entity;

@Entity(name = "ST_Instructor")
public class Instructor extends User {

    String companyName;
}
