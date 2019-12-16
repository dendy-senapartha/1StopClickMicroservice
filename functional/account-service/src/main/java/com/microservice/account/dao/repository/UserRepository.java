package com.microservice.account.dao.repository;


import com.microservice.account.dao.UserDao;
import com.microservice.account.model.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/*
 * Created by dendy-prtha on 01/03/2019.
 * User Repository class
 */

@Transactional
@Component
@RequiredArgsConstructor
public class UserRepository implements UserDao {

    Logger log = Logger.getLogger(UserRepository.class.getSimpleName());

    private final EntityManager entityManager;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByEmail(String email) {
        String hql = "FROM User user WHERE user.email = :email";
        log.info(hql);
        Query query = entityManager.createQuery(hql);
        query.setParameter("email", email);
        List<User> results = query.getResultList();
        User user = null;
        for (User e : results) {
            user = e;
            String passs = passwordEncoder.encode(user.getPassword());
            log.info("raw password : " + user.getPassword() + ", encrypted pasword : " + passs);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findById(Long aLong) {
        String hql = "FROM User user WHERE user.id = :id";
        log.info(hql);
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", aLong);
        List<User> results = query.getResultList();
        User user = null;
        for (User e : results) {
            user = e;
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean save(User user) {
        boolean status;
        try {
            entityManager.persist(user);
            status = true;
        } catch (HibernateException ex) {
            log.warning("exception: " + ex);
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
