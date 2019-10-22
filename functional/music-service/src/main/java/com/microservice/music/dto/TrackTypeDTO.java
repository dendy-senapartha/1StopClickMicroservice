package com.microservice.music.dto;

/*
 * Created by dendy-prtha on 17/05/2019.
 * Track type DTO
 */

import lombok.Data;

@Data
public class TrackTypeDTO {

    private int id;

    private String code;

    private String name;
}
