package com.microservice.movie.repository.dao;



import com.microservice.movie.model.Product;

import java.util.List;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Product Dao
 */

public interface ProductDao extends Dao<Product, Integer> {
    List<Product> getMovieByGenre(String genereId);
}
