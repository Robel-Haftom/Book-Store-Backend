package com.robel.bookstore.dto;

public record OrderItemResponseDTO(
        Long orderItemId,
        int quantity,
        double unitPrice,
        double totalPrice,
        String bookName,
        String userName
) {
}
