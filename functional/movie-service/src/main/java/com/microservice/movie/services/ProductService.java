package com.microservice.movie.services;

import com.microservice.movie.dto.MovieDTO;
import com.microservice.movie.dto.response.DefaultResponse;
import com.microservice.movie.model.Product;
import com.microservice.movie.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/*
 * Created by dendy-prtha on 16/12/2019.
 * service for product
 */


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public DefaultResponse getAllMovie() {
        List<Product> productList = productRepository.findAll();
        if (!productList.isEmpty()) {
            List<MovieDTO> movieDTOS = new ArrayList<>();
            for (Product product : productList) {
                movieDTOS.add(modelMapper.map(product, MovieDTO.class));
            }
            return new DefaultResponse(true, "Movies found!", movieDTOS);
        } else {
            return new DefaultResponse(false, "No Movies found!", null);
        }
    }

    public DefaultResponse getMovieByGenre(int genreId) {
        List<Product> productList = productRepository.getMovieByGenre(genreId + "");
        if (!productList.isEmpty()) {
            List<MovieDTO> movieDTOS = new ArrayList<>();
            for (Product product : productList) {
                movieDTOS.add(modelMapper.map(product, MovieDTO.class));
            }
            return new DefaultResponse(true, "Movies found!", movieDTOS);
        } else {
            return new DefaultResponse(false, "No Movies found!", null);
        }
    }

    public DefaultResponse getMovieById(int movieId) {
        Optional<Product> product = productRepository.findById(movieId);
        if (product.isPresent()) {
            return new DefaultResponse(true, "Movies found!", modelMapper.map(product.get(), MovieDTO.class));
        } else {
            return new DefaultResponse(false, "No Movies found!", null);
        }
    }
}