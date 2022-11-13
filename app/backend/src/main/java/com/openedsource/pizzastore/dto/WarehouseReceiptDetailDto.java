package com.openedsource.pizzastore.dto;

import lombok.Data;

@Data
public class WarehouseReceiptDetailDto {

    private int id;

    private int pizza_detail_id;

    private int warehouse_receipt_id;

    private float price;

    private int quantity;
}
