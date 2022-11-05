package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "topping_detail")
@Data
public class ToppingDetail {

    @Id
    @Column(name = "pizza_id")
    private int pizzaid;

//    @Id
    @Column(name = "topping_id")
    private int toppingid;
}
