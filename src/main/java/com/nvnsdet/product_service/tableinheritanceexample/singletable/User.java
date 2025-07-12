package com.nvnsdet.product_service.tableinheritanceexample.singletable;


import jakarta.persistence.*;

@Entity(name = "ST_User")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {

    @Id
    Long id;
    String name;
    String email;
}
