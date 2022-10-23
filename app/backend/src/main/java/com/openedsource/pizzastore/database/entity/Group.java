package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "group")
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String display;
}
