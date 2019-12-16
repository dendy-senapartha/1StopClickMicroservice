package com.microservice.auth.repository;

import com.microservice.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/*
 * Created by dendy-prtha on 01/03/2019.
 * User Repository class
 */

@Transactional
@Repository
public interface RoleRepository extends JpaRepository<Role, Serializable> {
    Optional<Role> findById(Long aLong);

    List findAll();
}
