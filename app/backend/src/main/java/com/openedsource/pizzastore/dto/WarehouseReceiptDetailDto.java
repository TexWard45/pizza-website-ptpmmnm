package com.openedsource.pizzastore.dto;

import lombok.Data;

@Data
public class WarehouseReceiptDetailDto {

    private int pizzadetailid;

    private int warehousereceiptid;

    private float price;

    private int amount;
}
