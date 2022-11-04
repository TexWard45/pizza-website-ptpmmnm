package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "warehouse_receipt")
@Data
public class WarehouseReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "supplier_id")
    private int supplierid;

    private String handler;

    private LocalDate time_created;
}
