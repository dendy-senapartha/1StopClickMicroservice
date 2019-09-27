package com.microservice.music.dto;

import java.util.Date;

/*
 * Created by dendy-prtha on 21/05/2019.
 * artist DTO
 */

public class ArtistDTO {

    private int id;

    private String firstName;

    private String lastName;

    private Date dob;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
