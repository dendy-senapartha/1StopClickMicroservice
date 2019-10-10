package com.microservice.movie.controller;

import com.microservice.movie.dto.MovieDTO;
import com.microservice.movie.dto.request.GetMovieByGenreRequest;
import com.microservice.movie.dto.request.GetMovieByIdRequest;
import com.microservice.movie.model.Product;
import com.microservice.movie.model.Video;
import com.microservice.movie.repository.dao.ProductDao;
import com.microservice.movie.repository.dao.VideoDao;
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

    private final ProductDao productRepository;

    private final VideoDao videoRepository;

    private final ModelMapper modelMapper;

    @GetMapping(value = "/get-all-movie",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllMovie() {
        List<MovieDTO> movieDTO = new ArrayList<>();
        List<Product> movieList = productRepository.findAll();
        for (Product product : movieList) {
            movieDTO.add(modelMapper.map(product, MovieDTO.class));
        }
        return ResponseEntity.ok(movieDTO);
    }

    @PostMapping(value = "/get-movie-by-genre",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMovieByGenre(@RequestBody GetMovieByGenreRequest request) {
        List<MovieDTO> movieDTO = new ArrayList<>();
        List<Product> movieList = productRepository.getMovieByGenre(request.getGenreId() + "");
        for (Product product : movieList) {
            movieDTO.add(modelMapper.map(product, MovieDTO.class));
        }
        return ResponseEntity.ok(movieDTO);
    }

    @PostMapping(value = "/get-movie-by-id",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMovieById(@RequestBody GetMovieByIdRequest request) {
        MovieDTO result = new MovieDTO();
        Optional<Product> movie = productRepository.findById(request.getId());
        if (movie.isPresent()) {
            result = modelMapper.map(movie.get(), MovieDTO.class);
        }
        return ResponseEntity.ok(result);
    }

}
