package com.openedsource.pizzastore.dto;


import lombok.Data;

@Data
public class PizzaDto {

    private int id;

    private int category_id;

    private String display;

    private String description;

    private String image;
}
