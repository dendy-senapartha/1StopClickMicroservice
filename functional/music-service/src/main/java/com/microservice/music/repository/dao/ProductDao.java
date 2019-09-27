package com.microservice.music.repository.dao;


import com.microservice.music.model.Product;

import java.util.List;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Product Dao
 */

public interface ProductDao extends Dao<Product, Integer> {
    List<Product> findAllProductByCategoryId(int catId) ;
    List<Product> findProductByCategoryIdAndTitle(int catId, String title);
    List<Product> getBuyedProductOfUserByCategory(int catId, String userId);
    List<Product> findBuyedProductOfUserByCategoryAndProdId(int catId, String userId, String productId);
    List<Product> findBuyedProductOfUserByCategoryAndProductName(int catId, String userId, String productName);
    List<Product> getAlbumProducts(String albumId);
    List<Product> findBuyedProductByUserIdAndAlbumId(String userId, String albumId);
    Product checkIfProductAlreadyOrdered(String userId, String productId);
}
