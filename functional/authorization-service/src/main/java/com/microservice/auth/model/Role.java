package com.microservice.auth.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/*
 * Created by dendy-prtha on 19/09/2019.
 * Role entity
 */

@Entity
@Table(name = "roles")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleType name;
    @Column(name = "description")
    private String description;
    @Column(name = "created_on")
    private Long createdOn;
    @Column(name = "modified_on")
    private Long modifiedOn;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

}
