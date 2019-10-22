package com.microservice.order.repository.external;

import com.microservice.order.model.external.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * Created by dendy-prtha on 04/10/2019.
 * auth microservice
 */

@FeignClient(name = "authorization")
public interface AuthServiceClient {

    @GetMapping(value = "/info", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<User> getUser();
}
