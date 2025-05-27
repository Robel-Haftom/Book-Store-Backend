package com.robel.bookstore.dto;

public record BookmarkResponseDTO(
        Long bookmarkId,
        String userName,
        String bookName
) {
}
