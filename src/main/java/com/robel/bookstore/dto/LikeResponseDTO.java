package com.robel.bookstore.dto;

public record LikeResponseDTO(
        Long likeId,
        String userName,
        String bookName,
        String createdAt
) {
}
