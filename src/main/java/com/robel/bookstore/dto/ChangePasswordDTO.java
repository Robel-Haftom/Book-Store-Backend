package com.robel.bookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ChangePasswordDTO {

    @NotBlank(message = "Old password cannot be blank")
    @Size(min = 6, max = 50, message = "Old password is not correct")
    private String oldPassword;

    @NotBlank(message = "New password cannot be blank")
    @Size(min = 6, max = 50, message = "New password must be between 6 and 50 characters")
    private String newPassword;

    @NotBlank(message = "Confirm password cannot be blank")
    @Size(min = 6, max = 50, message = "Confirm password must be between 6 and 50 characters")
    private String confirmPassword;
}
