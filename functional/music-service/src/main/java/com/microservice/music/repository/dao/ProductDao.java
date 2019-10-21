package com.microservice.music.repository.dao;


import com.microservice.music.model.Product;

import java.util.List;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Product Dao
 */

public interface ProductDao extends Dao<Product, Integer> {
    List<Product> getAlbumProducts(String albumId);
}
