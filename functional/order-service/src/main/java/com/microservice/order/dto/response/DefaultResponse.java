package com.microservice.order.dto.response;

import lombok.Data;
/*
 * Created by dendy-prtha on 17/05/2019.
 *
 */

@Data
public class DefaultResponse {

    private boolean success;
    private String message;
    private Object data;

    public DefaultResponse()
    {

    }

    public DefaultResponse(boolean success, String message, Object data)
    {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
