package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "order_detail")
@Data
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int order_id;

    private int pizza_detail_id;

    private float price;

    private int quantity;

}
