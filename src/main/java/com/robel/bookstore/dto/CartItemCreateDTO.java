package com.robel.bookstore.dto;

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

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    private Long userId;

    @NotNull
    private Long bookId;

}
