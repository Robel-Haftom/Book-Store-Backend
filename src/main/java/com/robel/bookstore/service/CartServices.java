package com.robel.bookstore.service;

import com.robel.bookstore.dto.CartResponseDTO;
import com.robel.bookstore.entity.Cart;
import com.robel.bookstore.entity.CartItem;

import java.util.List;

public interface CartServices {
    void updateCartStatus(Cart  cart);

    CartResponseDTO getCartByUserId(Long userId);
}
