package com.wishlist.app.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class WishListEmptyException extends RuntimeException {
    public WishListEmptyException(String message) {
        super(message);
    }
}
