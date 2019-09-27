package com.microservice.music.dto;

import java.util.Date;

/*
 * Created by dendy-prtha on 21/05/2019.
 * DTO for Album request
 */

public class AlbumDTO {

    private int id;

    private String name;

    private Date releaseDate;

    private String albumImageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAlbumImageUrl() {
        return albumImageUrl;
    }

    public void setAlbumImageUrl(String albumImageUrl) {
        this.albumImageUrl = albumImageUrl;
    }
}
