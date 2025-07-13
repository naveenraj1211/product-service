package com.nvnsdet.product_service.tableinheritanceexample.joinedclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "JC_Mentor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Mentor extends User {
    Double rating;
}
