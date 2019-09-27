package com.microservice.music.repository.dao;


import com.microservice.music.model.Track;

import java.util.List;
/*
 * Created by dendy-prtha on 11/06/2019.
 * Video Dao
 */

public interface TrackDao extends Dao<Track, Integer> {
    List<Track> findTrackByProductId(String productId);
    List<Track> getTracksByAlbumAndUserId(String productId);
}
