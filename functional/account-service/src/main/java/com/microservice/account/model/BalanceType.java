package com.microservice.account.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

/*
 * Created by dendy-prtha on 17/01/2020.
 * balance type model
 */

@Entity
@Table(name = "balance_type")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class BalanceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;
}