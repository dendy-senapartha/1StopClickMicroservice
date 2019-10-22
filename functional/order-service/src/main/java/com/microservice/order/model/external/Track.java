package com.microservice.order.model.external;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by dendy-prtha on 21/05/2019.
 * Track DTO
 */

@Data
public class Track {

    private int id;

    private TrackType trackType;

    private String streamUrl;

    private int length;

    private List<Artist> artists = new ArrayList<>();
}
