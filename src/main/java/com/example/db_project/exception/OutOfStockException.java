package com.example.db_project.exception;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String title, int stock, int quantity) {
        super(title + "재고 : " + stock + "구매요청 : " + quantity);
    }
}
