package com.example.db_project.domain;

import com.example.db_project.exception.OutOfStockException;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false, columnDefinition = "INT CHECK (price >= 0)")
    private int price;

    @Column(nullable = false, columnDefinition = "INT CHECK (price >= 0)")
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    protected Book(){

    }

    public Book(String title, String author, int price, int stock){
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }

    public void removeStock(int quantity){
        if(stock-quantity<0){
            throw new OutOfStockException(this.title, stock, quantity);
        }else {
            stock -= quantity;
        }

    }

    public void addStock(int quantity){
        stock += quantity;

    }

    void assignCategory(Category category){
        this.category=category;
    }
}
