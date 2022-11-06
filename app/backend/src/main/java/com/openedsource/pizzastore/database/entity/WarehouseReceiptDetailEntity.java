package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "warehouse_receipt_detail")
@Data
public class WarehouseReceiptDetailEntity {

//    @Id
    @Column(name = "pizza_detail_id")
    private int pizzadetailid;

    @Id
    @Column(name = "warehouse_receipt_id")
    private int warehousereceiptid;

    private float price;

    private int amount;


}
