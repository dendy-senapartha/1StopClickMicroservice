package com.microservice.music.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by dendy-prtha on 21/05/2019.
 * Track DTO
 */

@Data
public class TrackDTO {

    private int id;

    private TrackTypeDTO trackType;

    private String streamUrl;

    private int length;

    private List<ArtistDTO> artists = new ArrayList<>();
}
