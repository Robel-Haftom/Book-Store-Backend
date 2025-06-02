package com.robel.bookstore.dto;

public record LoginResponseDTO(
        UserResponseDTO userResponseDTO,
        String accessToken
) {

}
