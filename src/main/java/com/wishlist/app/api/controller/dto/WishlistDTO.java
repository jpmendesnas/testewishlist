package com.wishlist.app.api.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("clientId")
	private String clientId;


	@JsonProperty("productIds")
	private Set<String> productIds;


}
