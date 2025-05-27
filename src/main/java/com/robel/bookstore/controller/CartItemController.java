package com.robel.bookstore.controller;

import com.robel.bookstore.dto.CartItemCreateDTO;
import com.robel.bookstore.dto.CartItemResponseDTO;
import com.robel.bookstore.service.CartItemServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/cartItems")
public class CartItemController {

    @Autowired
    private CartItemServices cartItemServices;

    @PreAuthorize("hasRole('USER')")
    @PostMapping()
    public ResponseEntity<CartItemResponseDTO> addCartItem(@Valid @RequestBody CartItemCreateDTO cartItemCreateDTO){
        return ResponseEntity.ok().body(cartItemServices.addCartItem(cartItemCreateDTO));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("{cartItemId}")
    public ResponseEntity<CartItemResponseDTO> updateCartItem(@PathVariable("cartItemId") Long cartItemId,
                                                              @RequestBody Map<String, Integer> body){
        Integer quantity = body.get("quantity");
        return ResponseEntity.ok().body(cartItemServices.updateCartItem(cartItemId, quantity));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("{cartItemId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable("cartItemId") Long cartItemId){
        cartItemServices.deleteCartItem(cartItemId);
        return ResponseEntity.ok().body("Cart item deleted successfully");
    }
}
