package com.microservice.movie.repository;

import com.microservice.movie.model.Product;
import com.microservice.movie.repository.dao.ProductDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Implementation of Movie DAO
 */

@Transactional
@Component
public class ProductRepository implements ProductDao {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Product> findById(Integer id) {
        String hql = "FROM Product prdct WHERE prdct.id = :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        //List result = query.list();
        List<Product> results = query.getResultList();
        //session.close();
        Product product1 = null;
        for (Product product : results) {
            product1 = product;
        }
        return Optional.ofNullable(product1);
    }

    @Override
    public List<Product> findAll() {
        String hql = "FROM Product";
        Query query = entityManager.createQuery(hql);
        List<Product> results = query.getResultList();
        return results;
    }

    @Override
    public boolean save(Product o) {
        return false;
    }

    @Override
    public boolean update(Product o) {
        return false;
    }

    @Override
    public boolean delete(Product o) {
        return false;
    }

    @Override
    public List<Product> getMovieByGenre(String genreId) {
        String hql = "SELECT prdct FROM Product prdct join prdct.genres gnrs WHERE gnrs.id = " + genreId;
        Query query = entityManager.createQuery(hql);
        List<Product> results = query.getResultList();
        return results;
    }
}
