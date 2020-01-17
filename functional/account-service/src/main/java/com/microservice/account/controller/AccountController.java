package com.microservice.account.controller;

import com.microservice.account.dto.request.GetUserBalanceRequest;
import com.microservice.account.dto.response.DefaultResponse;

import com.microservice.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Created by dendy-prtha on 01/03/2019.
 * User REST Controller for user
 */

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService userService;

    @GetMapping("/get-balance")
    public ResponseEntity<DefaultResponse> getBalance(@RequestBody GetUserBalanceRequest request) {
        return ResponseEntity.ok(userService.getUserBalance(request.getUserId()));
    }

}