package com.openedsource.pizzastore.database.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "topping")
@Data
public class ToppingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String display;
}

