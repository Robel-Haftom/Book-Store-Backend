package com.robel.bookstore.dto;

import java.time.LocalDate;

public record ReviewResponseDTO(
        Long reviewId,
        String rating,
        String comment,
        String userName,
        String bookName,
        String createdAt
) {
}
