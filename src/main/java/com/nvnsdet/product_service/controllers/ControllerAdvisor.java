package com.nvnsdet.product_service.controllers;


import com.nvnsdet.product_service.dtos.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(value = {IllegalArgumentException.class, IndexOutOfBoundsException.class, Exception.class})
    public ResponseEntity<?> handleException(Exception e) {
        // Log the exception (optional)
        System.err.println("Exception occurred: " + e.getMessage());

        // Return a generic error response
        return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());

    }
}
