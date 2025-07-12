package com.nvnsdet.product_service.tableinheritanceexample.tableperclass;

import jakarta.persistence.Entity;

@Entity(name = "TPC_TA")
public class TA extends User {
    int numOfHelpSessions;
}
