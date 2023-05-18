package com.wishlist.app.api.controller;

import com.wishlist.app.api.controller.dto.RequestProductDTO;
import com.wishlist.app.api.service.WishlistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Api(value = "Wishlist", description = "Gerenciamento de wishlist", tags = {"WishListEndPoint"})
@RestController
@RequestMapping(WishlistRestController.BASE_URL)
public class WishlistRestController {

	static final String BASE_URL = "/wishlist";

	@Autowired
	private WishlistService wishlistService;


	@ApiOperation(value = "Adicionar um novo produto à wishlist", response = ResponseEntity.class)
	@PostMapping(consumes={"application/json"})
	public ResponseEntity<Object> addProduct(@Valid @RequestBody RequestProductDTO requestProductDTO) {
			return ResponseEntity.status(HttpStatus.CREATED).body(wishlistService.addProduct(requestProductDTO));
	}

	@ApiOperation(value = "Remover um produto da wishlist", response = ResponseEntity.class)
	@DeleteMapping("/{clientId}/{productId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeProduct(@PathVariable String clientId, @PathVariable String productId) {
		wishlistService.removeProduct(clientId, productId);
	}

	@ApiOperation(value = "Busca uma wishlist com todos os produtos com o id do cliente", response = ResponseEntity.class)
	@GetMapping("/{clientId}")
	public ResponseEntity<Object> getWishlist(@PathVariable String clientId) {
		return ResponseEntity.status(HttpStatus.OK).body(wishlistService.getWishlist(clientId));

	}

	@ApiOperation(value = "Verifica se um produto ja esta na wishlist de um cliente", response = ResponseEntity.class)
	@GetMapping("/{clientId}/{productId}")
	public ResponseEntity<Object> isProductInWishlist(@PathVariable String clientId, @PathVariable String productId) {
		return ResponseEntity.status(HttpStatus.OK).body( wishlistService.isProductInWishlist(clientId, productId));
	}
}
