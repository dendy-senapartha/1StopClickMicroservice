package com.microservice.music.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/*
 * Created by dendy-prtha on 05/04/2019.
 * Category Model
 */

@Data
public class CategoryDTO {

    private int id;

    private String name;

    private String target;

    private boolean isActive;

    private Date created;

}
