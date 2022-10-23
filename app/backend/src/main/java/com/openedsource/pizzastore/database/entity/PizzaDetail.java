package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
@Table(name = "pizza_detail")
@Data
public class PizzaDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "pizza_id")
    private int pizzaid;

    @Column(name = "size_id")
    private int sizeid;

    @Column(name = "base_id")
    private int baseid;

    private DecimalFormat price;

    private int quantity;



}
