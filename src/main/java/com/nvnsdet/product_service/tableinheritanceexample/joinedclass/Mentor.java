package com.nvnsdet.product_service.tableinheritanceexample.joinedclass;

import com.nvnsdet.product_service.tableinheritanceexample.singletable.User;
import jakarta.persistence.Entity;

@Entity(name = "JC_Mentor")
public class Mentor  extends User {

    Double rating;
}
