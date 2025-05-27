package com.robel.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationExceptionHandler(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<Map<String, String>> emilExistExceptionHandler(EmailExistException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", exception.getMessage())
        );
    }

    @ExceptionHandler(UserNameExistException.class)
    public ResponseEntity<Map<String, String>> userNameExistExceptionHandler(UserNameExistException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", exception.getMessage())
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> userNotFoundExceptionHandler(UserNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", exception.getMessage())
        );
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<Map<String, String>> incorrectPasswordHandler(PasswordIncorrectException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", exception.getMessage())
        );
    }
    @ExceptionHandler(CategoryExistException.class)
    public ResponseEntity<Map<String, String>> categoryExistExceptionHandler(CategoryExistException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", exception.getMessage())
        );
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> categoryNotFoundExceptionHandler(CategoryNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", exception.getMessage())
        );
    }
    @ExceptionHandler(BookExistException.class)
    public ResponseEntity<Map<String, String>> bookExistExceptionHandler(BookExistException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", exception.getMessage())
        );
    }
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Map<String, String>> bookNotFoundExceptionHandler(BookNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", exception.getMessage())
        );
    }

    @ExceptionHandler(CartItemExistException.class)
    public ResponseEntity<Map<String, String>> cartItemExistExceptionHandler(CartItemExistException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", exception.getMessage())
        );
    }
    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Map<String, String>> cartNotFoundExceptionHandler(CartNotFoundException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", exception.getMessage())
        );
    }
    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<Map<String, String>> cartItemNotFoundExceptionHandler(CartItemNotFoundException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", exception.getMessage())
        );
    }
    @ExceptionHandler(EmptyCartException.class)
    public ResponseEntity<Map<String, String>> emptyCartExceptionHandler(EmptyCartException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", exception.getMessage())
        );
    }
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, String>> orderNotFoundExceptionHandler(OrderNotFoundException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", exception.getMessage())
        );
    }
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Map<String, String>> InsufficientStockException(InsufficientStockException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", exception.getMessage())
        );
    }
    @ExceptionHandler(BookOutOfStockException.class)
    public ResponseEntity<Map<String, String>> BookOutOfStockException(BookOutOfStockException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", exception.getMessage())
        );
    }

}
