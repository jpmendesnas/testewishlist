package com.wishlist.app.api.service;

import com.wishlist.app.api.controller.dto.RequestProductDTO;
import com.wishlist.app.api.controller.dto.WishlistDTO;

public interface WishlistService {


    WishlistDTO addProduct(RequestProductDTO requestProductDTO);

    WishlistDTO removeProduct(String clientId, String productId);

    WishlistDTO getWishlist(String clientId);

    boolean isProductInWishlist(String clientId, String productId);
}
