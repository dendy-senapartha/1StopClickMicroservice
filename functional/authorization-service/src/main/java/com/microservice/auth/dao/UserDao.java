package com.microservice.auth.dao;

import com.microservice.auth.model.User;

import java.util.Optional;

/*
 * Created by dendy-prtha on 01/03/2019.
 * Provides interface specific to {@link User} data
 */

public interface UserDao extends Dao<User, Long> {

    /**
     * Find {@link User} by its email.
     *
     * @param email email     *
     * @return user
     */
    Optional<User> findByEmail(String email);
}
