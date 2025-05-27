package com.robel.bookstore.service;

import com.robel.bookstore.dto.CartItemCreateDTO;
import com.robel.bookstore.dto.CartItemResponseDTO;

public interface CartItemServices {

    CartItemResponseDTO addCartItem(CartItemCreateDTO cartItemCreateDTO);

    CartItemResponseDTO updateCartItem(Long cartItemId, Integer quantity);

    void deleteCartItem(Long cartItemId);
}
