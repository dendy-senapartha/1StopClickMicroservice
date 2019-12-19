package com.microservice.music.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

/*
 * Created by dendy-prtha on 05/04/2019.
 * Product model
 */

@Entity
@Table(name = "product")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "package_code")
    private BigDecimal packageCode;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    @Type(type = "text")
    private String description;

    @Column(name = "compatibility")
    private String compatibility;

    @Column(name = "status")
    private String status;

    @Column(name = "created", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("product")
    private List<ProductImage> productImageList;

    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("product")
    private List<Track> trackList;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "product_subcategory",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "subcategory_id", referencedColumnName = "id")})
    private List<Subcategory> subcategories ;

    public void addSubcategory(Subcategory subcategory) {
        subcategories.add(subcategory);
        subcategory.getProducts().add(this);
    }

    public void removeSubcategory(Subcategory subcategory) {
        subcategory.getProducts().remove(this);
        subcategories.remove(subcategory);
    }

    public void removeAllSubcategory() {
        for (Subcategory subcategory : subcategories) {
            subcategory.getProducts().remove(this);
        }
        subcategories.clear();
    }

}
