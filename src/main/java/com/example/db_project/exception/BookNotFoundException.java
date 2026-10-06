package com.example.db_project.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super(id.toString());
    }
}
