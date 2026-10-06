package com.example.db_project.domain;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private int totalPrice;

    @Column(nullable = false)
    private LocalDateTime orderedAt;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OrderStatus status;

    @Column(nullable = false,updatable = false)
    private LocalDateTime creatAt;

    protected Order(){

    }

    public enum OrderStatus{
        ORDERED, CANCELED
    }
}
