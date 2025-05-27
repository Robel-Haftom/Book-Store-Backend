package com.robel.bookstore.mapper;

import com.robel.bookstore.dto.CartItemCreateDTO;
import com.robel.bookstore.dto.CartItemResponseDTO;
import com.robel.bookstore.entity.CartItem;

public class CartItemMapper {
    public static CartItem mapToCartItem(CartItemCreateDTO cartItemCreateDTO){
        return CartItem.builder()
                .quantity(cartItemCreateDTO.getQuantity())
                .build();
    }

    public static CartItemResponseDTO mapToCartItemResponseDTO(CartItem cartItem){
        return new CartItemResponseDTO(
                cartItem.getCartItemId(),
                cartItem.getQuantity(),
                cartItem.getUnitePrice(),
                cartItem.getTotalPrice(),
                cartItem.getCart().getUser().getUserName(),
                cartItem.getBook().getBookTitle()
        );
    }
}
