package com.openedsource.pizzastore.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class OrderDetailDto {

    private int id;

    private int order_id;

    private int pizza_detail_id;

    private float price;

    private int quantity;
}
