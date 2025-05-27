package com.robel.bookstore.dto;


import java.time.LocalDate;
import java.util.List;

public record UserResponseDTO(
        Long userId,
        String firstName,
        String lastName,
        String userName,
        String email,
        String phoneNumber,
        String role,
        String profileImgUrl,
        String gender,
        String dateOfBirth,
        String addressLine1,
        String addressLine2,
        String city,
        String state,
        String postalCode,
        String country,
        List<ReviewResponseDTO> reviews,
        List<LikeResponseDTO> likes,
        List<BookmarkResponseDTO> bookmarks,
        List<OrderResponseDTO> orders
) {
}
