package com.example.library_online.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String author_full_name;
    private String transcript;
    private String created_date;
    private Integer prize;
    private Integer age_access;
    private Boolean exist = true;

    @ManyToOne()
    private Type type;

    @ManyToOne()
    private Customer customer;



}
