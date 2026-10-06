package com.example.db_project.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Long id) {
        super(id.toString());
    }
}
