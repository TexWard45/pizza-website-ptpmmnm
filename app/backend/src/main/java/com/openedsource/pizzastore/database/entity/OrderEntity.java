package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {
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

    private String email;

    private int payment_type;

    private int order_type;

    @Column(name = "order_time")
    private LocalDate ordertime;

    private String note;
}
