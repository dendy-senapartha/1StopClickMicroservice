package com.microservice.music.dto;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by dendy-prtha on 21/05/2019.
 * Track DTO
 */
public class TrackDTO {

    private int id;

    private TrackTypeDTO trackType;

    private String streamUrl;

    private int length;

    private List<ArtistDTO> artists = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public TrackTypeDTO getTrackType() {
        return trackType;
    }

    public void setTrackType(TrackTypeDTO trackType) {
        this.trackType = trackType;
    }

    public List<ArtistDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistDTO> artists) {
        this.artists = artists;
    }
}
