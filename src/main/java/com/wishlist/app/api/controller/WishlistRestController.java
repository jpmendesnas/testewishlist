package com.wishlist.app.api.controller;

import com.wishlist.app.api.controller.dto.RequestProductDTO;
import com.wishlist.app.api.service.WishlistService;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(value = "Wishlist", description = "Gerenciamento de wishlist", tags = {"WishListEndPoint"})
@RestController
@RequestMapping(WishlistRestController.BASE_URL)
public class WishlistRestController {

	static final String BASE_URL = "/wishlist";

	@Autowired
	private WishlistService wishlistService;


	@PostMapping(consumes={"application/json"})
	public ResponseEntity<Object> addProduct(@Valid @RequestBody RequestProductDTO requestProductDTO) {
			return ResponseEntity.status(HttpStatus.CREATED).body(wishlistService.addProduct(requestProductDTO));
	}

	@DeleteMapping("/{clientId}/{productId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeProduct(@PathVariable String clientId, @PathVariable String productId) {
		wishlistService.removeProduct(clientId, productId);
	}

	@GetMapping("/{clientId}")
	public ResponseEntity<Object> getWishlist(@PathVariable String clientId) {
		return ResponseEntity.status(HttpStatus.OK).body(wishlistService.getWishlist(clientId));

	}

	@GetMapping("/{clientId}/{productId}")
	public ResponseEntity<Object> isProductInWishlist(@PathVariable String clientId, @PathVariable String productId) {
		return ResponseEntity.status(HttpStatus.OK).body( wishlistService.isProductInWishlist(clientId, productId));
	}
}
