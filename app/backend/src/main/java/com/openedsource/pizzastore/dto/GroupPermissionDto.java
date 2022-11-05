package com.openedsource.pizzastore.dto;

import lombok.Data;

@Data
public class GroupPermissionDto {

    private int id;

    private int group_id;

    private String permission;

    private int value;
}
