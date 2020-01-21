package com.microservice.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
/*
 * Created by dendy-prtha on 05/03/2019.
 * model for user_profile table of database
 */

@Entity
@Table(name = "user_profile")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "dob")
    private String dob;
    @Column(name = "phone")
    private String phone;
    @Column(name = "image_url")
    private String imageUrl;

    @OneToOne
    @MapsId
    @JsonIgnoreProperties("userProfile")
    private User user;

    public UserProfile() {
    }

    public UserProfile(Long id, String name, String dob, String phone, String imageUrl) {
        this.setId(id);
        this.setName(name);
        this.setDob(dob);
        this.setPhone(phone);
        this.setImageUrl(imageUrl);
    }

    @Override
    public String toString() {
        return "UserProfile [id=" + getId() + ", name=" + name +
                ", dob=" + dob + ", phone=" + phone + ", imageUrl=" + "]";
    }
}