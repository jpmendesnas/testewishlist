package com.wishlist.app.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorInfo> handleNotFoundException(NotFoundException ex) {
        String mensagemErro = ex.getMessage();
        ErrorInfo errorInfo = new ErrorInfo(mensagemErro);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);
    }

    @ExceptionHandler(WishlistLimitExceededException.class)
    public ResponseEntity<ErrorInfo> handleWishlistLimitExceededException(WishlistLimitExceededException ex) {
        String mensagemErro = ex.getMessage();
        ErrorInfo errorInfo = new ErrorInfo(mensagemErro);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);
    }

    @ExceptionHandler(WishListEmptyException.class)
    public ResponseEntity<ErrorInfo> handleWishListEmptyException(WishListEmptyException ex) {
        String mensagemErro = ex.getMessage();
        ErrorInfo errorInfo = new ErrorInfo(mensagemErro);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorInfo);
    }

    // Outros manipuladores de exceção


}
