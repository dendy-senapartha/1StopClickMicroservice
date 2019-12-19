package com.microservice.movie.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

/*
 * Created by dendy-prtha on 17/05/2019.
 * director
 */

@Entity
@Table(name = "director")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
