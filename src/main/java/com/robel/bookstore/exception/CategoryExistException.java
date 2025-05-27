package com.robel.bookstore.exception;

public class CategoryExistException extends RuntimeException {
    public CategoryExistException(String message) {
        super(message);
    }
}
