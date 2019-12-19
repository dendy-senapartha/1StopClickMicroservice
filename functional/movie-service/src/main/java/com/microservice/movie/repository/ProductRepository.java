package com.microservice.movie.repository;

import com.microservice.movie.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Implementation of Movie DAO
 */

@Transactional
@Component
public interface ProductRepository extends JpaRepository<Product, Serializable> {

    @Query("SELECT prdct FROM Product prdct join prdct.genres gnrs WHERE gnrs.id = :genreId")
    List<Product> getMovieByGenre(@Param("genreId") String genreId);
}
