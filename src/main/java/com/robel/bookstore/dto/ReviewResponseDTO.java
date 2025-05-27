package com.robel.bookstore.dto;

import java.time.LocalDate;

public record ReviewResponseDTO(
        Long reviewId,
        int rating,
        String comment,
        String userName,
        String bookName,
        String createdAt
) {
}
