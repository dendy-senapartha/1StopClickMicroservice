package com.microservice.music.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Created by dendy-prtha on 05/04/2019.
 * Subcategory
 */


@Entity
@Table(name = "subcategory")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "target")
    @Getter
    @Setter
    private String target;

    @Column(name = "priority")
    @Getter
    @Setter
    private int priority;

    @Column(name = "is_active")
    @Getter
    @Setter
    private boolean isActive;

    @Column(name = "created", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date created;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @Setter
    private Category category;

    @ManyToMany(mappedBy = "subcategories")
    @Getter
    @Setter
    private List<Product> products ;


    public Category getCategory() {
        return (Category) Hibernate.unproxy(category);
    }
}
