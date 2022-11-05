package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "group_permission")
@Data
public class GroupPermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int group_id;

    private String permission;

    @Column(length = 1)
    private int value;
}