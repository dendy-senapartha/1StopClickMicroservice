package com.microservice.order.model.external;

import lombok.Data;

import java.util.Date;

/*
 * Created by dendy-prtha on 21/05/2019.
 * artist DTO
 */

@Data
public class Artist {

    private int id;

    private String firstName;

    private String lastName;

    private Date dob;
}
