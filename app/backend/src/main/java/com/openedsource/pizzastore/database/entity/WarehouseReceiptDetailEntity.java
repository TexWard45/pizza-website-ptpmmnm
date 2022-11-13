package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "warehouse_receipt_detail")
@Data
public class WarehouseReceiptDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int pizza_detail_id;

    private int warehouse_receipt_id;

    private float price;

    private int quantity;

}
