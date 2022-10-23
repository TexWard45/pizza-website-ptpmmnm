package com.openedsource.pizzastore.database.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "topping")
@Data
public class Topping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String display;
}

