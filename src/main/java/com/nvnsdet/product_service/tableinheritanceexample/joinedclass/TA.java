package com.nvnsdet.product_service.tableinheritanceexample.joinedclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;


@Entity(name = "JC_TA")
@PrimaryKeyJoinColumn(name = "user_id")
public class TA extends User {

    int numOfHelpSessions;
}
