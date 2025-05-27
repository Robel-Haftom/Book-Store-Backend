package com.robel.bookstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginRequestDTO {

    @NotBlank
    @Email
    @Size(min = 5)
    private String email;

    @NotBlank
    @Size(min = 6, max = 50)
    private String password;
}
