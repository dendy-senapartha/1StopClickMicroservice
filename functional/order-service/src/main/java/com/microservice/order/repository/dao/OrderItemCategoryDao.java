package com.microservice.order.repository.dao;

import com.microservice.order.model.OrderItemCategory;

/*
 * Created by dendy-prtha on 11/06/2019.
 * Video Dao
 */

public interface OrderItemCategoryDao extends Dao<OrderItemCategory, Integer> {

    OrderItemCategory findByName(String name);
}
