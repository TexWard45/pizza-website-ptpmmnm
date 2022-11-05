package com.openedsource.pizzastore.dto;


import lombok.Data;

@Data
public class PizzaDto {

    private int id;

    private int categoryid;

    private String display;

    private String description;

    private String image;
}
