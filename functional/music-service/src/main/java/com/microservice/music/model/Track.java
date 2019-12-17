package com.microservice.music.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by dendy-prtha on 21/05/2019.
 * Track Entity
 */

@Entity
@Table(name = "tracks")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "product_id")
    @Setter
    private Product product;

    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "track_type_id")
    @Setter
    private TrackType trackType;

    @Column(name = "stream_url")
    @Getter
    @Setter
    private String streamUrl;

    @Column(name = "length")
    @Getter
    @Setter
    private int length;

    @ManyToOne
    @JsonIgnoreProperties("tracks")
    @Getter
    @Setter
    private Album album;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="track_artist",
            joinColumns={@JoinColumn(name="track_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="artist_id", referencedColumnName="id")})
    @JsonIgnoreProperties("tracks")
    @Getter
    @Setter
    private List<Artist> artists;

    public Product getProduct() {
        return (Product) Hibernate.unproxy(product);
    }

    public TrackType getTrackType() {
        return (TrackType) Hibernate.unproxy(trackType);
    }
}
