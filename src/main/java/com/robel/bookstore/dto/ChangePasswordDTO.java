package com.robel.bookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ChangePasswordDTO {

    @NotBlank
    @Size(min = 8, max = 50)
    private String oldPassword;

    @NotBlank
    @Size(min = 8, max = 50)
    private String newPassword;

    @NotBlank
    @Size(min = 8, max = 50)
    private String confirmPassword;
}
