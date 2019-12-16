package com.microservice.auth.repository;

import com.microservice.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/*
 * Created by dendy-prtha on 01/03/2019.
 */

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long aLong);

    List<User> findAll();
}
