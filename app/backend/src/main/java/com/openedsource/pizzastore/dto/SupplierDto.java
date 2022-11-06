package com.openedsource.pizzastore.dto;

import lombok.Data;


@Data
public class SupplierDto {

    private int id;

    private String display;

    private String address;

    private String phone;

    private String email;
}
