package com.microservice.auth.dto;
import com.microservice.auth.model.RoleType;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/*
 * Created by dendy-prtha on 19/09/2019.
 * Role entity
 */

@Data
public class RoleDTO {
    private long id;

    private RoleType name;

    private String description;

    private Long createdOn;

    private Long modifiedOn;

}
