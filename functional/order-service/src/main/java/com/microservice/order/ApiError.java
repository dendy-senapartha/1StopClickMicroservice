package com.microservice.order;

import lombok.Data;
import org.springframework.http.HttpStatus;

/*
 * Created by dendy-prtha on 11/10/2019.
 * Api error
 */

@Data
public class ApiError {

    private HttpStatus status;
    private String message;

    public ApiError(HttpStatus status, String messages) {
        super();
        this.status = status;
        this.message = messages;
    }

}
