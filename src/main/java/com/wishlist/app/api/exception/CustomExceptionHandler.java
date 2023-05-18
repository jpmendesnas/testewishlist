package com.wishlist.app.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorInfo> handleWishListBadRequest(BadRequestException ex) {
        String mensagemErro = ex.getMessage();
        ErrorInfo errorInfo = new ErrorInfo(mensagemErro);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);
    }



    // Outros manipuladores de exceção


}
