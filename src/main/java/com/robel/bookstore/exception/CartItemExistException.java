package com.robel.bookstore.exception;

public class CartItemExistException extends RuntimeException{
    public CartItemExistException(String message){
        super(message);
    }
}
