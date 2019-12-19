package com.microservice.music.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

/*
 * Created by dendy-prtha on 15/05/2019.
 * ProductImageType
 */

@Entity
@Table(name = "image_type_product")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class ProductImageType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

}
