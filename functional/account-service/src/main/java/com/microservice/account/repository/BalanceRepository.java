package com.microservice.account.repository;

import com.microservice.account.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

/*
 * Created by dendy-prtha on 01/03/2019.
 * Provides interface specific to {@link User} data
 */

@Transactional
@Repository
public interface BalanceRepository extends JpaRepository<Balance, Serializable> {
    Optional<Balance> findByUserId(Long aLong);
}
