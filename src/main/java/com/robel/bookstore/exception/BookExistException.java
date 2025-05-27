package com.robel.bookstore.exception;

public class BookExistException extends RuntimeException {
    public BookExistException(String message) {
        super(message);
    }
}
