package com.microservice.music.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/*
 * Created by dendy-prtha on 05/04/2019.
 * Category Model
 */

@Entity
@Table(name = "category")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "target")
    private String target;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "created", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
}
