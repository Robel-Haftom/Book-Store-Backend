package com.robel.bookstore.controller.user;

import com.robel.bookstore.dto.OrderResponseDTO;
import com.robel.bookstore.service.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/users/orders")
public class UserOrderController {

    @Autowired
    private OrderServices orderServices;


    @PreAuthorize("hasRole('USER')")
    @PostMapping()
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody Map<String, Long> body){
        Long cartId = body.get("cartId");
        return ResponseEntity.ok().body(orderServices.createOrder(cartId));
    }
}
