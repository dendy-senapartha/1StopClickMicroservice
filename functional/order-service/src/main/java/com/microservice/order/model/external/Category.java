package com.microservice.order.model.external;

import lombok.Data;

import java.util.Date;

/*
 * Created by dendy-prtha on 05/04/2019.
 * Category Model
 */

@Data
public class Category {

    private int id;

    private String name;

    private String target;

    private boolean isActive;

    private Date created;

}
