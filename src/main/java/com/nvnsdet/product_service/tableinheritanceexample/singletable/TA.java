package com.nvnsdet.product_service.tableinheritanceexample.singletable;

import jakarta.persistence.Entity;

@Entity(name = "ST_TA")
public class TA extends User  {

    int numOfHelpSessions;
}
