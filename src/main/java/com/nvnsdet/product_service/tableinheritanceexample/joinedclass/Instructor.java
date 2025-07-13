package com.nvnsdet.product_service.tableinheritanceexample.joinedclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "JC_Instructor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Instructor  extends User {

    String companyName;
}
