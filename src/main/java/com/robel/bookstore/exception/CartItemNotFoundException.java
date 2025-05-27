package com.robel.bookstore.exception;

public class CartItemNotFoundException extends RuntimeException{
    public CartItemNotFoundException(String message){
        super(message);
    }
}
