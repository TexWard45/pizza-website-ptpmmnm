package com.openedsource.pizzastore.dto;

import lombok.Data;

@Data
public class PizzaDetailDto {

    private int id;

    private int pizzaid;

    private int sizeid;

    private int baseid;

    private float price;

    private int quantity;

}
