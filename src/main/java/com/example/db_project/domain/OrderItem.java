package com.example.db_project.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "order_items",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_record_order_itesm",
                        columnNames = {"book_id", "order_id"}
                )
        }
)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int orderPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    protected OrderItem(){

    }

    public OrderItem(Book book, int quantity){
        this.quantity = quantity;
        this.orderPrice = book.getPrice();
        this.book = book;
    }

    void assignOrder(Order order) { this.order = order; }
}
