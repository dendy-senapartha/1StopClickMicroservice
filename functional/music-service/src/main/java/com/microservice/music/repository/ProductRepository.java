package com.microservice.music.repository;


import com.microservice.music.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Implementation of Movie DAO
 */

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Serializable> {

    @Query( "SELECT DISTINCT prdct FROM Product prdct " +
            "INNER JOIN prdct.trackList track " +
            "INNER JOIN track.album albm " +
            "WHERE albm.id = :albumId")
     List<Product> getAlbumProducts(@Param("albumId") int albumId);
}
