package com.microservice.music.repository;

import com.microservice.music.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/*
 * Created by dendy-prtha on 21/06/2019.
 * Album Repository
 */

@Transactional
@Repository
public interface AlbumRepository extends JpaRepository<Album, Serializable> {

    /*
    @Query("SELECT DISTINCT albm FROM Invoice invc " +
            "INNER JOIN invc.user usr " +
            "INNER JOIN invc.orders ordrs " +
            "INNER JOIN ordrs.orderItemList ordritm " +
            "INNER JOIN ordritm.product prdct " +
            "INNER JOIN prdct.trackList track " +
            "INNER JOIN track.album albm " +
            "WHERE usr.id = :userId")
    List<Album> getBuyedAlbumOfUser(@Param("userId") String userId);
    */
}
