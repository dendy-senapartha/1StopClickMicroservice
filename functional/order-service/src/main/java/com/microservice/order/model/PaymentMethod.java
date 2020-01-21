package com.microservice.order.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

/*
 * Created by dendy-prtha on 31/05/2019.
 * balance type type entity
 */

@Entity
@Table(name = "payment_method")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;
}
