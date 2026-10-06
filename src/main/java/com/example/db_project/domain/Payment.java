package com.example.db_project.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
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
    private LocalDateTime paidAt = LocalDateTime.now();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",unique = true,nullable = false)
    private Order order;

    protected Payment(){

    }

    public Payment(Order order, int amount, PayMethod method){
        this.order = order;
        this.amount = amount;
        this.method = method;
    }

    public enum PayMethod {
        CARD, TRANSFER, POINT
    }

}
