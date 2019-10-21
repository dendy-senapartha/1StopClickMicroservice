package com.microservice.order.model.external;

import lombok.Data;

import java.math.BigDecimal;
import java.util.*;

/*
 * Created by dendy-prtha on 05/04/2019.
 * Song DTO. Song is combination of product and track
 */

@Data
public class Song {

    private int id;

    private String productName;

    private BigDecimal packageCode;

    private BigDecimal price;

    private String description;

    private String compatibility;

    private String status;

    private Date created;

    private Set<Subcategory> subcategories = new HashSet<>();

    private List<Track> trackList = new ArrayList<>();
}
