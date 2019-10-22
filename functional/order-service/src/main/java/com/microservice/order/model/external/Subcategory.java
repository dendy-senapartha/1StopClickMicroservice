package com.microservice.order.model.external;

import lombok.Data;

import java.util.Date;


/*
 * Created by dendy-prtha on 05/04/2019.
 * Subcategory
 */

@Data
public class Subcategory {

    private int id;

    private String name;

    private String target;

    private int priority;

    private boolean isActive;

    private Date created;

    private Category category;
}
