package com.robel.bookstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginRequestDTO {

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    private String password;
}
