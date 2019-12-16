package com.microservice.movie.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/*
 * Created by dendy-prtha on 15/05/2019.
 * Product Image Model
 */

@Entity
@Table(name = "product_image")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image_url")
    private String imageUrl;
}
