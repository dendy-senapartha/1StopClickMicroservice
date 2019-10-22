package com.microservice.order.model.external;

import lombok.Data;

import java.math.BigDecimal;
import java.util.*;

/*
 * Created by dendy-prtha on 05/04/2019.
 * movie DTO
 */
@Data
public class Movie {

    private int id;

    private String productName;

    private BigDecimal packageCode;

    private BigDecimal price;

    private String description;

    private String compatibility;

    private String status;

    private Date created;

    private List<ProductImage> productImageList = new ArrayList<>();

    private Set<Genre> genres = new HashSet<>();
}
