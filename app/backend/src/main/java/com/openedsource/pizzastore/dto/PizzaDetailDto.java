package com.openedsource.pizzastore.dto;

import lombok.Data;

@Data
public class PizzaDetailDto {

    private int id;

    private int pizza_id;

    private int size_id;

    private int base_id;

    private float price;

    private int quantity;

}
