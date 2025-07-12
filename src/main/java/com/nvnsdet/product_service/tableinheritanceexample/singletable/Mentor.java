package com.nvnsdet.product_service.tableinheritanceexample.singletable;


import jakarta.persistence.Entity;

@Entity(name = "TPC_Mentor")
public class Mentor extends User  {

    Double rating;
}
