package com.microservice.order.repository;

import com.microservice.order.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/*
 * Created by dendy-prtha on 04/06/2019.
 * Repository for order
 */

@Transactional
@Repository
public interface OrderRepository extends JpaRepository<Orders, Serializable> {

    @Query("SELECT DISTINCT ords FROM Orders ords " +
            "INNER JOIN ords.invoice invc " +
            "WHERE invc.userId= :userId " +
            "AND (invc.status NOT LIKE 'DRAFT' AND invc.status NOT LIKE 'ISSUED')")
    List<Orders> getFinishedOrderByUserId(@Param("userId") long userId);

    @Query("SELECT DISTINCT ords FROM Orders ords " +
            "INNER JOIN ords.invoice invc " +
            "WHERE invc.userId=:userId " +
            "AND (invc.status LIKE 'DRAFT' OR invc.status LIKE 'ISSUED')")
    List<Orders> getUserOrderNeedTooPay(@Param("userId") long userId) ;

    @Query("SELECT DISTINCT ords FROM Orders ords " +
            "INNER JOIN ords.invoice invc " +
            "WHERE invc.userId= :userId " +
            "AND invc.status LIKE 'DRAFT'")
    List<Orders> findUserDraftOrder(@Param("userId") long userId);

}
