package com.robel.bookstore.service.impl;

import com.robel.bookstore.dto.CartResponseDTO;
import com.robel.bookstore.entity.Cart;
import com.robel.bookstore.entity.CartItem;
import com.robel.bookstore.exception.CartNotFoundException;
import com.robel.bookstore.mapper.CartMapper;
import com.robel.bookstore.repository.CartRepository;
import com.robel.bookstore.service.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CartServicesImpl implements CartServices{

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void updateCartStatus(Cart cart) {
        double totalPrice = 0;
        int totalQuantity = 0;
        for(CartItem item : cart.getCartItems()){
            totalPrice += item.getTotalPrice();
            totalQuantity += item.getQuantity();
        }

        cart.setTotalPrice(totalPrice);
        cart.setTotalQuantity(totalQuantity);
        cart.setUpdatedAt(LocalDateTime.now());

        if(totalPrice < 2000) { cart.setDiscountPercentage(0.0);};
        if(totalPrice >= 2000) { cart.setDiscountPercentage(5.0);}
        if(totalPrice >= 5000) { cart.setDiscountPercentage(10.0);}
        if(totalPrice >= 10000) { cart.setDiscountPercentage(15.0);}
        if(totalPrice >= 50000) { cart.setDiscountPercentage(20.0);}
        if(totalPrice >= 2000 && totalQuantity >= 10 ) { cart.setDiscountPercentage(7.0);}
        if(totalPrice >= 5000 && totalQuantity >= 10) { cart.setDiscountPercentage(14.0);}
        if(totalPrice >= 10000 && totalQuantity >= 10) { cart.setDiscountPercentage(21.0);}
        if(totalPrice >= 50000 && totalQuantity >= 10) { cart.setDiscountPercentage(25.0);}

        cart.setDiscountAmount(totalPrice * cart.getDiscountPercentage()/100);
        cart.setFinalAmount(totalPrice - cart.getDiscountAmount());

        cartRepository.save(cart);
    }

    @Override
    public CartResponseDTO getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new CartNotFoundException("No cart found with this user id: " + userId));
        return CartMapper.mapToCartResponseDTO(cart);
    }
}
