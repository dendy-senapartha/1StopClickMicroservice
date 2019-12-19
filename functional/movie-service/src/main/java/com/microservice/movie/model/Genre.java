package com.microservice.movie.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Created by dendy-prtha on 26/09/2019.
 * Genre model
 */

@Entity
@Table(name = "genre")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Product> products;
}
