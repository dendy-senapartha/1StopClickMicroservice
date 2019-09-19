package com.microservice.auth.dao.repository;

import com.microservice.auth.dao.UserDao;
import com.microservice.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/*
 * Created by dendy-prtha on 01/03/2019.
 * User Repository class
 */

@Transactional
@Component
@RequiredArgsConstructor
public class UserRepository implements UserDao {

    private final EntityManager entityManager;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByEmail(String email) {
        String hql = "FROM User user WHERE user.email = :email";
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        query.setParameter("email", email);
        List<User> results = query.getResultList();
        User user = null;
        for (User e : results) {
            user = e;
            String passs = passwordEncoder.encode(user.getPassword());
            System.out.println("raw password : " + user.getPassword() + ", encrypted pasword : " + passs);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findById(Long aLong) {
        String hql = "FROM User user WHERE user.id = :id";
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", aLong);
        List<User> results = query.getResultList();
        //session.close();
        User user = null;
        for (User e : results) {
            user = e;
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        String hql = "FROM User user";
        Query query = entityManager.createQuery(hql);
        List<User> results = query.getResultList();
        return results;
    }

    @Override
    public boolean save(User user) {
        boolean status;
        try {
            entityManager.persist(user);
            status = true;
        } catch (HibernateException ex) {
            System.out.println("exception: " + ex);
            status = false;
        }

        return status;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }
}
