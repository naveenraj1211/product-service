package com.nvnsdet.product_service.tableinheritanceexample.joinedclass;

import com.nvnsdet.product_service.tableinheritanceexample.singletable.User;
import jakarta.persistence.Entity;


@Entity(name = "JC_TA")
public class TA extends User {

    int numOfHelpSessions;
}
