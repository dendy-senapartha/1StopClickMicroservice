package com.microservice.account.service;


import com.microservice.account.dto.response.DefaultResponse;
import com.microservice.account.model.Balance;
import com.microservice.account.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

/*
 * Created by dendy-prtha on 12/12/2019.
 * service layer for account
 */

@Service
@RequiredArgsConstructor
public class AccountService {

    Logger log = Logger.getLogger(AccountService.class.getSimpleName());

    private final BalanceRepository balanceRepository;

    public DefaultResponse getUserBalance(long userId) {

        Optional<Balance> balanceOptional = balanceRepository.findByUserId(userId);
        if (balanceOptional.isPresent()) {
            return new DefaultResponse(true, "User Updated!",balanceOptional.get());
        } else {
            return new DefaultResponse(false, "User Not found!", null);
        }
    }
}
