package com.openedsource.pizzastore.dto;

import lombok.Data;

@Data
public class OrderDetailDto {

    private int id;

    private int orderid;

    private int pizzadetailid;

    private float price;

    private int quantity;
}
