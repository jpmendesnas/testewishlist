package com.wishlist.app.api.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RequestProductDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	@JsonProperty("clientId")
	private String clientId;


	@NotNull
	@NotEmpty
	@JsonProperty("productId")
	private String productId;


}
