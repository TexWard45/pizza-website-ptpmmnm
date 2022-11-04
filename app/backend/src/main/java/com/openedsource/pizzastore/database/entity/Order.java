package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "order")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 32)
    private String customer;

    @Column(length = 32)
    private String handler;


    private float total_price;

    private int quantity;

    private String fullname;

    private String address;

    @Column(length = 10)
    private String phone;

    private int payment_type;

    private int order_type;

    @Column(name = "order_time")
    private LocalDate ordertime;

    private String note;
}
