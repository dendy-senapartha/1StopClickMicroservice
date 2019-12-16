package com.microservice.movie.repository;

import com.microservice.movie.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
/*
 * Created by dendy-prtha on 11/06/2019.
 * video repository
 */

@Transactional
@Repository
public interface VideoRepository extends JpaRepository<Video, Serializable> {

    @Query("FROM Video video WHERE video.product.id = :productId")
    List<Video> findVideoByProductId(@Param("productId") String productId);
}
