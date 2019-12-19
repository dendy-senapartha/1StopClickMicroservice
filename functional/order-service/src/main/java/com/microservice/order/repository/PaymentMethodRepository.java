package com.microservice.order.repository;

import com.microservice.order.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
/*
 * Created by dendy-prtha on 11/06/2019.
 * video repository
 */

@Transactional
@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Serializable> {

}
