package com.example.db_project.domain;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@Transient
    private int totalPrice;*/

    @Column(nullable = false)
    private LocalDateTime orderedAt;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OrderStatus status;

    @Column(nullable = false,updatable = false)
    private LocalDateTime creatAt = LocalDateTime.now();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();



    protected Order(){

    }

    public static Order create(Member member){
        Order order = new Order();
        order.member = member;
        order.orderedAt = LocalDateTime.now();
        order.status = OrderStatus.ORDERED;
        return order;
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.assignOrder(this);
    }

    public int getTotalPrice() {
        int totalPrice=0;
        for(OrderItem entry : orderItems){
            totalPrice += entry.getQuantity() * entry.getOrderPrice();
        }
        return totalPrice;
    }

    public enum OrderStatus{
        ORDERED, CANCELED
    }
}
