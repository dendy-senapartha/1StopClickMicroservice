package com.microservice.auth.dao.repository;

import com.microservice.auth.dao.RoleDao;
import com.microservice.auth.dao.UserDao;
import com.microservice.auth.model.Role;
import com.microservice.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
public class RoleRepository implements RoleDao {

    private final EntityManager entityManager;

    private final PasswordEncoder passwordEncoder;


    @Override
    public Optional<Role> findById(Long aLong) {
        String hql = "FROM Role role WHERE role.id = :id";
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", aLong);
        List<Role> results = query.getResultList();
        //session.close();
        Role currentRole = null;
        for (Role role : results) {
            currentRole = role;
        }
        return Optional.ofNullable(currentRole);
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public boolean save(Role o) {
        return false;
    }

    @Override
    public boolean update(Role o) {
        return false;
    }

    @Override
    public boolean delete(Role o) {
        return false;
    }

}
