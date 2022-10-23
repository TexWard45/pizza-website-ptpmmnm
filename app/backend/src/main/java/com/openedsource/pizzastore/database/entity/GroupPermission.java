package com.openedsource.pizzastore.database.entity;

import lombok.Data;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import javax.persistence.*;

@Entity
@Table(name = "group_permission")
@Data
public class GroupPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int group_id;

    private String permission;

    private TinyIntTypeDescriptor value;
}