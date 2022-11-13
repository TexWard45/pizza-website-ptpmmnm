package com.openedsource.pizzastore.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WarehouseReceiptDto {

    private int id;

    private int supplier_id;

    private String handler;

    private float total_price;

    private int quantity;

    private LocalDate time_created;
}
