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
 * entity Album
 */

@Entity
@Table(name = "albums")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "album_image_url")
    private String albumImageUrl;

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("album")
    private List<Track> tracks;

}
