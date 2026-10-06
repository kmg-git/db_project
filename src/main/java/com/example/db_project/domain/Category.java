package com.example.db_project.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 30)
    private String name;

    protected Category(){

    }
    public Category(String name){
        this.name = name;
    }
}
