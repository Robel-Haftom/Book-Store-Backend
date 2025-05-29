package com.robel.bookstore.exception;

public class LikeExistException extends RuntimeException{
    public LikeExistException(String message){
        super(message);
    }
}
