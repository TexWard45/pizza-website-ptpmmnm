package com.openedsource.pizzastore.dto;

import lombok.Data;
import java.time.LocalDate;
@Data
public class OrderDto {
    private int id;

    private String customer;

    private String handler;

    private float total_price;

    private int quantity;

    private String fullname;

    private String address;

    private String phone;

    private String email;

    private int payment_type;

    private int order_type;

    private LocalDate ordertime;

    private String note;
}
