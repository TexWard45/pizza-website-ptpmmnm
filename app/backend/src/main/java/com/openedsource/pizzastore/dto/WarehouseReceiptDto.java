package com.openedsource.pizzastore.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WarehouseReceiptDto {

    private int id;

    private int supplierid;

    private String handler;

    private LocalDate time_created;
}
