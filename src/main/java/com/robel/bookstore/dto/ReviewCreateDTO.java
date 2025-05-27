package com.robel.bookstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCreateDTO {

    @NotNull
    @Min(0)
    private Integer rating;

    @NotBlank
    private String comment;

    @NotNull
    Long bookId;

    @NotNull
    Long userId;

}
