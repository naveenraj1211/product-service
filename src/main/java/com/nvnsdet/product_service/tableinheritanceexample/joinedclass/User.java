package com.nvnsdet.product_service.tableinheritanceexample.joinedclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name = "JC_User")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    Long id;
    String name;
    String email;
}
