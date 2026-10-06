package com.example.db_project.domain;


import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100, unique = true)
    private String email;


    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private MemberGrade grade;

    @Column
    private int point;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt=LocalDateTime.now();;




    protected Member(){
    }

    public Member(String name, String email){
        this.name = name;
        this.email = email;
    }


    public enum MemberGrade{
        NORMAL,GOLD
    }

}
