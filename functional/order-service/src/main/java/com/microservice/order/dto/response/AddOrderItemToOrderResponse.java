package com.microservice.order.dto.response;

/*
 * Created by dendy-prtha on 17/05/2019.
 *
 */

public class AddOrderItemToOrderResponse {

    private String status;

    private String itemId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
