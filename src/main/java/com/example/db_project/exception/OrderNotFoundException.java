package com.example.db_project.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super(id.toString());
    }
}
