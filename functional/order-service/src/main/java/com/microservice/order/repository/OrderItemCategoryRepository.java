package com.microservice.order.repository;

import com.microservice.order.model.OrderItemCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
/*
 * Created by dendy-prtha on 11/06/2019.
 * order item category repository
 */

@Transactional
@Repository
public interface OrderItemCategoryRepository extends JpaRepository<OrderItemCategory, Serializable> {

    @Query("FROM OrderItemCategory cat WHERE cat.name LIKE :name")
    OrderItemCategory findByName(@Param("name") String name);
}
