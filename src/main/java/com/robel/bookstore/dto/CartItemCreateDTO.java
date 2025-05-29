package com.robel.bookstore.dto;

import com.robel.bookstore.validation.OnCreate;
import com.robel.bookstore.validation.OnUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemCreateDTO {

    @NotNull(message = "Quantity cannot be null", groups = {OnCreate.class})
    @Min(value = 1, message = "Quantity must be at least 1", groups = {OnCreate.class, OnUpdate.class})
    private Integer quantity;

    @NotNull(message = "User ID cannot be null", groups = {OnCreate.class})
    private Long userId;

    @NotNull(message = "Book ID cannot be null", groups = {OnCreate.class})
    private Long bookId;

}
