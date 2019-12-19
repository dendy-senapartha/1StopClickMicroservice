package com.microservice.movie.dto;

import com.microservice.movie.model.ProductImage;
import lombok.Data;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.util.*;

/*
 * Created by dendy-prtha on 05/04/2019.
 * movie DTO
 */

@Data
public class MovieDTO {

    private int id;

    private String productName;

    private BigDecimal packageCode;

    private BigDecimal price;

    private String description;

    private String compatibility;

    private String status;

    private Date created;

    private List<ProductImage> productImageList = new ArrayList<>();

    private Set<GenreDTO> genres = new HashSet<>();
}
