package com.microservice.music.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 21/05/2019.
 * artist entity
 */

@Entity
@Table(name = "artist")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob")
    private Date dob;

    //bidirectional mapping. it refrence the atribut on the employee entity
    @ManyToMany(mappedBy="artists", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnoreProperties("artists")
    private List<Track> tracks ;
}
