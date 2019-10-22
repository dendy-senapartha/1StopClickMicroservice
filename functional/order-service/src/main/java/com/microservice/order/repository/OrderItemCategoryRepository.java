package com.microservice.order.repository;

import com.microservice.order.model.OrderItemCategory;
import com.microservice.order.model.PaymentMethod;
import com.microservice.order.repository.dao.OrderItemCategoryDao;
import com.microservice.order.repository.dao.PaymentMethodDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
/*
 * Created by dendy-prtha on 11/06/2019.
 * video repository
 */

@Transactional
@Component
public class OrderItemCategoryRepository implements OrderItemCategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<OrderItemCategory> findById(Integer id) {
        String hql = "FROM OrderItemCategory cat WHERE cat.name = " + id;
        Query query = entityManager.createQuery(hql);
        List<OrderItemCategory> results = query.getResultList();
        OrderItemCategory orderItemCategory = null;
        for (OrderItemCategory e : results) {
            orderItemCategory = e;
        }
        return Optional.ofNullable(orderItemCategory);
    }

    @Override
    public List<OrderItemCategory> findAll() {
        return null;
    }

    @Override
    public boolean save(OrderItemCategory paymentMethod) {
        return false;
    }

    @Override
    public boolean update(OrderItemCategory paymentMethod) {
        return false;
    }

    @Override
    public boolean delete(OrderItemCategory paymentMethod) {
        return false;
    }

    @Override
    public OrderItemCategory findByName(String name) {
        String hql = "FROM OrderItemCategory cat WHERE cat.name LIKE '" + name + "'";
        Query query = entityManager.createQuery(hql);
        List<OrderItemCategory> results = query.getResultList();
        OrderItemCategory orderItemCategory = null;
        for (OrderItemCategory e : results) {
            orderItemCategory = e;
        }
        return orderItemCategory;
    }
}
