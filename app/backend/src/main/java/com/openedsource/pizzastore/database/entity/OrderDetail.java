package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
@Table(name = "order_detail")
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_id")
    private int orderid;

    @Column(name = "pizza_detail_id")
    private int pizzadetailid;

    private DecimalFormat price;

    private int quantity;

}
