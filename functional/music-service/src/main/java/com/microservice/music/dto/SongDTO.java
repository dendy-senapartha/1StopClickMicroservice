package com.microservice.music.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.*;

/*
 * Created by dendy-prtha on 05/04/2019.
 * Song DTO. Song is combination of product and track
 */

@Data
public class SongDTO {

    private int id;

    private String productName;

    private BigDecimal packageCode;

    private BigDecimal price;

    private String description;

    private String compatibility;

    private String status;

    private Date created;

    private Set<SubcategoryDTO> subcategories = new HashSet<>();

    private List<TrackDTO> trackList = new ArrayList<>();
}
