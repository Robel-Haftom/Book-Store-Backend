package com.robel.bookstore.dto;

public record CategoryResponseDTO(
        Long categoryId,
        String categoryName,
        int totalBooks
) {
}
