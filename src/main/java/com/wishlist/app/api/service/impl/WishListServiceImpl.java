package com.wishlist.app.api.service.impl;

import com.wishlist.app.api.controller.dto.RequestProductDTO;
import com.wishlist.app.api.controller.dto.WishlistDTO;
import com.wishlist.app.api.domain.Wishlist;
import com.wishlist.app.api.exception.*;
import com.wishlist.app.api.repository.WishlistRepository;
import com.wishlist.app.api.service.WishlistService;
import com.wishlist.app.api.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class WishListServiceImpl implements WishlistService {
	private static final int MAX_PRODUCTS = 20;

	@Autowired
	private WishlistRepository repository;

	static Logger log  = Logger.getLogger(String.valueOf(WishListServiceImpl.class));

	@Override
	public WishlistDTO addProduct(RequestProductDTO requestProductDTO) {

		Wishlist wishlist = repository.findByClientId(requestProductDTO.getClientId())
				.orElseGet(() -> new Wishlist(requestProductDTO));

		if (wishlist.getProductIds().size() >= MAX_PRODUCTS) {
			throw new WishlistLimitExceededException(MessageUtils.getMessage("limitItemsRequest"));
		}

		boolean added = wishlist.getProductIds().add(requestProductDTO.getProductId());

		if (added) {
			repository.save(wishlist);
		}
		return ModelMapperUtils.getModelMapper().map(wishlist, WishlistDTO.class);
	}


	@Override
	public WishlistDTO removeProduct(String clientId, String productId) {
		if (!StringUtils.hasText(clientId) || !StringUtils.hasText(productId)) {
			throw new BadRequestException(MessageUtils.getMessage("badRequest"));
		}

		Wishlist wishlist = repository.findByClientId(clientId)
				.orElseThrow(() -> new NotFoundException(MessageUtils.getMessage("notFoundError", clientId)));

		if (!wishlist.getProductIds().remove(productId)) {
			throw new NotFoundException(MessageUtils.getMessage("notFoundError", productId));
		}

		repository.save(wishlist);

		return ModelMapperUtils.getModelMapper().map(wishlist, WishlistDTO.class);
	}

	@Override
	public WishlistDTO getWishlist(String clientId) {
		if (!StringUtils.hasText(clientId)) {
			throw new BadRequestException(MessageUtils.getMessage("badRequest"));
		}
		Wishlist wishlist = repository.findByClientId(clientId)
				.orElseThrow(() -> new NotFoundException(MessageUtils.getMessage("notFoundError", clientId)));

		if (wishlist.getProductIds().isEmpty()) {
			throw new WishListEmptyException(MessageUtils.getMessage("notFoundError", clientId));
		}

		return ModelMapperUtils.getModelMapper().map(wishlist, WishlistDTO.class);
	}


	@Override
	public boolean isProductInWishlist(String clientId, String productId) {
		if (!StringUtils.hasText(clientId) || !StringUtils.hasText(productId)) {
			throw new BadRequestException(MessageUtils.getMessage("badRequest"));
		}

		Wishlist wishlist = repository.findByClientId(clientId)
				.orElseThrow(() -> new NotFoundException("Wishlist not found for client ID: " + clientId));
		return wishlist.getProductIds().contains(productId);
	}


}
