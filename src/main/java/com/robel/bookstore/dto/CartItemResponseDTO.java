package com.robel.bookstore.dto;

public record CartItemResponseDTO(
        Long cartItemId,
        int quantity,
        double unitPrice,
        double totalPrice,
        String userName,
        String bookName
){
}
