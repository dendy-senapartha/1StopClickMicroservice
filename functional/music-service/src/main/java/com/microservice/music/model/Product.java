package com.microservice.music.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
    private List<ProductImage> productImageList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("product")
    private List<Track> trackList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "product_subcategory",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "subcategory_id", referencedColumnName = "id")})
    private Set<Subcategory> subcategories = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(BigDecimal packageCode) {
        this.packageCode = packageCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<ProductImage> getProductImageList() {
        return productImageList;
    }

    public void setProductImageList(List<ProductImage> productImageList) {
        this.productImageList = productImageList;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

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
