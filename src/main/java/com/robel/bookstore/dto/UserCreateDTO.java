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

    @NotBlank(groups = OnCreate.class)
    @Size(min = 3, max = 50, groups = { OnCreate.class, OnUpdate.class})
    private String firstName;

    @NotBlank(groups = OnCreate.class)
    @Size(min = 3, max = 50, groups = { OnCreate.class, OnUpdate.class})
    private String lastName;

    @NotBlank(groups = OnCreate.class)
    @Size(min = 3, max = 50, groups = {OnCreate.class, OnUpdate.class})
    private String userName;

    @NotBlank(groups = OnCreate.class)
    @Email
    @Size(min=5, max = 255, groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @NotBlank(groups = OnCreate.class)
    @Size(min = 6, max = 50, groups = OnCreate.class)
    private String password;

    @NotBlank(groups = OnCreate.class)
    @Size(min = 10, max = 13, groups = {OnCreate.class, OnUpdate.class})
    private String phoneNumber;

    @NotBlank(groups = OnCreate.class)
    @Size(min = 1, max = 6, groups = {OnCreate.class, OnUpdate.class})
    private String gender;

    @NotNull(groups = OnCreate.class)
    private String dateOfBirth;

    @Size(max = 255, groups = {OnCreate.class, OnUpdate.class})
    private String addressLine1;

    @Size(max = 255, groups = {OnCreate.class, OnUpdate.class})
    private String addressLine2;

    @NotBlank(groups = OnCreate.class)
    @Size(max = 255, groups = {OnCreate.class, OnUpdate.class})
    private String city;

    @NotBlank(groups = OnCreate.class)
    @Size(max = 255, groups = { OnCreate.class, OnUpdate.class})
    private String state;

    @Size(max = 255,groups = { OnCreate.class, OnUpdate.class})
    private String postalCode;

    @NotBlank(groups = OnCreate.class)
    @Size(max = 255, groups = { OnCreate.class, OnUpdate.class})
    private String country;
}
