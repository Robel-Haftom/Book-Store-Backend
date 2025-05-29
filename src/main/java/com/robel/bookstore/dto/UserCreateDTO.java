package com.robel.bookstore.dto;

import com.robel.bookstore.validation.OnCreate;
import com.robel.bookstore.validation.OnUpdate;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDTO {

    @NotBlank(groups = OnCreate.class, message = "First name cannot be blank")
    @Size(min = 3, max = 50, groups = { OnCreate.class, OnUpdate.class}, message = "First name must be between 3 and 50 characters")
    private String firstName;

    @NotBlank(groups = OnCreate.class, message = "Last name cannot be blank")
    @Size(min = 3, max = 50, groups = { OnCreate.class, OnUpdate.class}, message = "Last name must be between 3 and 50 characters")
    private String lastName;

    @NotBlank(groups = OnCreate.class, message = "Username cannot be blank")
    @Size(min = 3, max = 50, groups = {OnCreate.class, OnUpdate.class}, message = "Username must be between 3 and 50 characters")
    private String userName;

    @NotBlank(groups = OnCreate.class, message = "Email cannot be blank")
    @Email
    @Size(min=5, max = 255, groups = {OnCreate.class, OnUpdate.class}, message = "Email must be between 5 and 255 characters")
    private String email;

    @NotBlank(groups = OnCreate.class, message = "Password cannot be blank")
    @Size(min = 6, max = 50, groups = OnCreate.class, message = "Password must be between 6 and 50 characters")
    private String password;

    @NotBlank(groups = OnCreate.class, message = "Phone number cannot be blank")
    @Size(min = 10, max = 13, groups = {OnCreate.class, OnUpdate.class}, message = "Phone number must be between 10 and 13 characters")
    private String phoneNumber;

    @NotBlank(groups = OnCreate.class, message = "Gender cannot be blank")
    @Size(min = 1, max = 6, groups = {OnCreate.class, OnUpdate.class}, message ="Gender must be between 1 and 6 characters")
    private String gender;

    @NotNull(groups = OnCreate.class, message = "Date of birth cannot be null")
    private String dateOfBirth;

    @Size(max = 255, groups = {OnCreate.class, OnUpdate.class}, message = "Address line 1 must be between 1 and 255 characters")
    private String addressLine1;

    @Size(max = 255, groups = {OnCreate.class, OnUpdate.class}, message = "Address line 2 must be between 1 and 255 characters")
    private String addressLine2;

    @NotBlank(groups = OnCreate.class, message = "City cannot be blank")
    @Size(max = 255, groups = {OnCreate.class, OnUpdate.class}, message = "City must be between 1 and 255 characters")
    private String city;

    @NotBlank(groups = OnCreate.class, message = "State cannot be blank")
    @Size(max = 255, groups = { OnCreate.class, OnUpdate.class}, message = "State must be between 1 and 255 characters")
    private String state;

    @Size(max = 255,groups = { OnCreate.class, OnUpdate.class}, message = "Postal code must be between 1 and 255 characters")
    private String postalCode;

    @NotBlank(groups = OnCreate.class, message = "Country cannot be blank")
    @Size(max = 255, groups = { OnCreate.class, OnUpdate.class}, message = "Country must be between 1 and 255 characters")
    private String country;
}
