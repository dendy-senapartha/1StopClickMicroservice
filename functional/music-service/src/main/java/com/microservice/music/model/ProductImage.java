package com.microservice.music.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/*
 * Created by dendy-prtha on 15/05/2019.
 * Product Image Model
 */

@Entity
@Table(name = "product_image")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "image_type_id")
    @Setter
    private ProductImageType productImageType;

    @Column(name = "image_url")
    @Getter
    @Setter
    private String imageUrl;

    public ProductImageType getProductImageType() {
        return (ProductImageType) Hibernate.unproxy(productImageType);
    }

}
