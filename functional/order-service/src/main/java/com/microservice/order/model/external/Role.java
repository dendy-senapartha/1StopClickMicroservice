package com.microservice.order.model.external;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
/*
 * Created by dendy-prtha on 19/09/2019.
 * Role entity
 */

@Data
public class Role {
    private long id;

    private RoleType name;

    private String description;

    private Long createdOn;

    private Long modifiedOn;
}
