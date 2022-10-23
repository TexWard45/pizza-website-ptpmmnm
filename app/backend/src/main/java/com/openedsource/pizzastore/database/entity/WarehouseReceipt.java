package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

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

    private Date time_created;
}
