package com.microservice.movie.controller;

import com.microservice.movie.dto.MovieDTO;
import com.microservice.movie.dto.request.GetMovieByGenreRequest;
import com.microservice.movie.dto.request.GetMovieByIdRequest;
import com.microservice.movie.dto.response.DefaultResponse;
import com.microservice.movie.model.Product;
import com.microservice.movie.model.Video;
import com.microservice.movie.repository.ProductRepository;
import com.microservice.movie.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * Created by dendy-prtha on 16/04/2019.
 * controller for product
 */

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final ProductService productService;

    @GetMapping(value = "/get-all-movie")
    public ResponseEntity<?> getAllMovie() {
        DefaultResponse response = productService.getAllMovie();
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getData());
        } else {
            return ResponseEntity.ok(response.getMessage());
        }
    }

    @PostMapping(value = "/get-movie-by-genre")
    public ResponseEntity<?> getMovieByGenre(@RequestBody GetMovieByGenreRequest request) {
        DefaultResponse response = productService.getMovieByGenre(request.getGenreId());
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getData());
        } else {
            return ResponseEntity.ok(response.getMessage());
        }
    }

    @PostMapping(value = "/get-movie-by-id",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMovieById(@RequestBody GetMovieByIdRequest request) {
        DefaultResponse response = productService.getMovieById(request.getId());
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getData());
        } else {
            return ResponseEntity.ok(response.getMessage());
        }
    }

}
