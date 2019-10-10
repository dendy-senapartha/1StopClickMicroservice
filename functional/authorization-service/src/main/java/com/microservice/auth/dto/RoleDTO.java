package com.microservice.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.microservice.auth.model.RoleType;
import com.microservice.auth.model.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/*
 * Created by dendy-prtha on 19/09/2019.
 * Role entity
 */

public class RoleDTO {
    private long id;

    private RoleType name;

    private String description;

    private Long createdOn;

    private Long modifiedOn;

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public Long getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Long modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
