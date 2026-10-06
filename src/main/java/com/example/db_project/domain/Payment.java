package com.example.db_project.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int amount;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PayMethod method;

    @Column(nullable = false,updatable = false)
    private LocalDateTime paidAt;



    public enum PayMethod {
        CARD, TRANSFER, POINT
    }

}
