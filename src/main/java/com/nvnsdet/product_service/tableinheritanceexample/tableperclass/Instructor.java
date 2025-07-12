package com.nvnsdet.product_service.tableinheritanceexample.tableperclass;

import jakarta.persistence.Entity;

@Entity(name = "TPC_Instructor")
public class Instructor extends User {
    String companyName;
}
