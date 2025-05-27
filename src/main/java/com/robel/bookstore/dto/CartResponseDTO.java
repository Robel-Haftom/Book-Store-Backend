package com.robel.bookstore.dto;

import java.util.List;

public record CartResponseDTO(
        Long cartId,
        double totalPrice,
        double discountAmount,
        double discountPercentage,
        double finalAmount,
        int totalQuantity,
        String userName,
        List<CartItemResponseDTO> cartItems
){
}
