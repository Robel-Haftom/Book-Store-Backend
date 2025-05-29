package com.robel.bookstore.exception;

public class BookmarkNotFoundException extends RuntimeException{
    public BookmarkNotFoundException(String message){
        super(message);
    }
}
