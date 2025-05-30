package com.robel.bookstore.controller.user;

import com.robel.bookstore.dto.CartResponseDTO;
import com.robel.bookstore.service.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/carts")
public class CartController {
    @Autowired
    private CartServices cartServices;

    @GetMapping("{userId}")
    public ResponseEntity<CartResponseDTO> getCartByUserId(@PathVariable("userId") Long userId){
        return ResponseEntity.ok().body(cartServices.getCartByUserId(userId));
    }
}
