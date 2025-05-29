package com.robel.bookstore.exception;

public class BookmarkExistException extends RuntimeException{
    public BookmarkExistException(String message){
        super(message);
    }
}
