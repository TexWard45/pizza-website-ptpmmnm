package com.openedsource.pizzastore.dto;


import lombok.Data;

import java.time.LocalDate;
@Data
public class StatusDetailDto {

    private int orderid;

    private int statusid;

    private LocalDate time_created;
}
