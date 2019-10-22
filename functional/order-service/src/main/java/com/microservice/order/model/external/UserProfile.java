package com.microservice.order.model.external;

import lombok.Data;

/*
 * Created by dendy-prtha on 17/05/2019.
 * User profile
 */
@Data
public class UserProfile {

    private Long id;

    private String name;

    private String dob;

    private String phone;

    private String image_url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
