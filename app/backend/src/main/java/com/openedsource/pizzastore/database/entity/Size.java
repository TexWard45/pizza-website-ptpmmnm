package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "size")
@Data
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String display;

    private int priority;

}