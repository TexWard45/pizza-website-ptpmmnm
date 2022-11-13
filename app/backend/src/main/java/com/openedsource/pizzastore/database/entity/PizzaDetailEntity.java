package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "pizza_detail")
@Data
public class PizzaDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int pizza_id;

    private int size_id;

    private int base_id;

    private float price;

    private int quantity;

}
