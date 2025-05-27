package com.robel.bookstore.exception;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserNameExistException extends RuntimeException {
    public UserNameExistException(String message) {
        super(message);
    }
}
