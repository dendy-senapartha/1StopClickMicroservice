package com.microservice.music.repository;

import com.microservice.music.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
/*
 * Created by dendy-prtha on 11/06/2019.
 * track repository
 */

@Transactional
@Repository
public interface TrackRepository extends JpaRepository<Track, Serializable> {

    @Query("FROM Track track WHERE track.product.id = :productId")
    List<Track> findTrackByProductId(@Param("productId") String productId);

    @Query("FROM Track track WHERE track.product.id = :productId")
    List<Track> getTracksByAlbumAndUserId(@Param("productId") String productId);
}
