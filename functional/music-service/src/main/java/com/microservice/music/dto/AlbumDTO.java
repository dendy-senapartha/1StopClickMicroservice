package com.microservice.music.dto;

import lombok.Data;

import java.util.Date;

/*
 * Created by dendy-prtha on 21/05/2019.
 * DTO for Album request
 */

@Data
public class AlbumDTO {

    private int id;

    private String name;

    private Date releaseDate;

    private String albumImageUrl;
}
