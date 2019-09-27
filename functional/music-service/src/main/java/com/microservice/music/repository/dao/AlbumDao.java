package com.microservice.music.repository.dao;

import com.microservice.music.model.Album;
import com.microservice.music.repository.dao.Dao;

import java.util.List;
/*
 * Created by dendy-prtha on 11/06/2019.
 * AlbumDao
 */

public interface AlbumDao extends Dao<Album, Integer> {
    List<Album> getBuyedAlbumOfUser(String userId);
}
