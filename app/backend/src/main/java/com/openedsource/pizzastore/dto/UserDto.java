package com.openedsource.pizzastore.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UserDto {

    private String username;

    private Integer groupid;

    private String password;

    private String cfpassword;

    private String fullname;

    private LocalDate birth;

    private String address;

    private String phone;

    private String email;
}
