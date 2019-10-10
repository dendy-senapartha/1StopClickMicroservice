package com.microservice.order.repository.external;

import com.microservice.order.dto.request.GetMovieByIdRequest;
import com.microservice.order.model.external.Movie;
import com.microservice.order.model.external.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


/*
 * Created by dendy-prtha on 04/10/2019.
 * auth microservice
 */

@FeignClient(name = "movie")
public interface MovieServiceClient {

    @GetMapping(value = "/get-movie-by-id", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Movie> getMovieById(@RequestBody GetMovieByIdRequest request);
}
