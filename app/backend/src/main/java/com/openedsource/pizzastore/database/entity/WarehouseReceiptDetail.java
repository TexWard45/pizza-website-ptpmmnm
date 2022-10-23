package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "warehouse_receipt_detail")
@Data
public class WarehouseReceiptDetail {

    @Id
    @Column(name = "ingredient_id")
    private int ingredientid;

    @Id
    @Column(name = "warehouse_receipt_id")
    private int warehousereceiptid;

    private String handler;

    private Date time_created;


}
