package com.microservice.auth.dto.response;

/*
 * Created by dendy-prtha on 17/05/2019.
 *
 */

public class BaseResponse {

    private String status = "";

    private String message = "";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}