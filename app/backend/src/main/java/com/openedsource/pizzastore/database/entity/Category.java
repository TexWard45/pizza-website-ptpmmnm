package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String display;
}
