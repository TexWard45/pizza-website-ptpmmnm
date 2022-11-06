package com.openedsource.pizzastore.database.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "status_detail")
@Data
public class StatusDetailEntity {
    @Id
    @Column(name = "order_id")
    private int orderid;

    @Column(name = "status_id")
    private int statusid;

    @Column(name = "time_created")
    private LocalDate timecreated;
}
