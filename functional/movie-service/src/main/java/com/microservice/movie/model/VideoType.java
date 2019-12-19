package com.microservice.movie.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

/*
 * Created by dendy-prtha on 17/05/2019.
 * video type
 */

@Entity
@Table(name = "video_type")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class VideoType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;
}
