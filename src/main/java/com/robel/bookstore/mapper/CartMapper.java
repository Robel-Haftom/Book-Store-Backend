package com.robel.bookstore.mapper;

import com.robel.bookstore.dto.CartResponseDTO;
import com.robel.bookstore.entity.Cart;

public class CartMapper {
    public static CartResponseDTO mapToCartResponseDTO(Cart cart){
        return new CartResponseDTO(
                cart.getCartId(),
                cart.getTotalPrice(),
                cart.getDiscountAmount(),
                cart.getDiscountPercentage(),
                cart.getFinalAmount(),
                cart.getTotalQuantity(),
                cart.getUser().getUserName(),
                cart.getCartItems().stream().map(CartItemMapper::mapToCartItemResponseDTO).toList()
        );
    }
}
