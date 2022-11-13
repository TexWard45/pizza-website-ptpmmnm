package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "warehouse_receipt")
@Data
public class WarehouseReceiptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int supplier_id;

    private String handler;

    private float total_price;

    private int quantity;

    private LocalDate time_created;
}
