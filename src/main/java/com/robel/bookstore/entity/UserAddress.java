package com.robel.bookstore.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserAddress {

    @Size(max = 255)
    private String addressLine1;

    @Size(max = 255)
    private String addressLine2;

    @NotBlank
    @Size(max = 255)
    private String city;

    @NotBlank
    @Size(max = 255)
    private String state;

    @Size(max = 255)
    private String postalCode;

    @NotBlank
    @Size(max = 255)
    private String country;

}
