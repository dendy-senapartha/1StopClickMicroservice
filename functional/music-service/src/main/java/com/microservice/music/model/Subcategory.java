package com.microservice.music.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
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
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "target")
    private String target;

    @Column(name = "priority")
    private int priority;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "created", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "subcategories")
    private Set<Product> products = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Category getCategory() {
        return (Category) Hibernate.unproxy(category);
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
