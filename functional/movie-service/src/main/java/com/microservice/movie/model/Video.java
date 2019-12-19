package com.microservice.movie.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 17/05/2019.
 * video
 */

@Entity
@Table(name = "video")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "video_type_id")
    private VideoType videoType;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "studio")
    private String studio;

    @Column(name = "age_rating")
    private int ageRating;

    @Column(name = "avg_rating")
    private float avgRating;

    @Column(name = "overall_rank")
    private int overallRank;

    @Column(name = "stream_url")
    private String streamUrl;

    @Column(name = "duration")
    private int duration;

    @ManyToMany
    @JoinTable(
            name="actor_video",
            joinColumns={@JoinColumn(name="video_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="actor_id", referencedColumnName="id")})
    private List<Actor> actors= new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="director_video",
            joinColumns={@JoinColumn(name="video_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="director_id", referencedColumnName="id")})
    private List<Director> directors= new ArrayList<>();
}
