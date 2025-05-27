package com.robel.bookstore.controller;

import com.robel.bookstore.dto.OrderResponseDTO;
import com.robel.bookstore.service.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    @PreAuthorize("hasRole('USER')")
    @PostMapping()
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody Map<String, Long> body){
        Long cartId = body.get("cartId");
        return ResponseEntity.ok().body(orderServices.createOrder(cartId));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable("orderId") Long orderId){
        return ResponseEntity.ok().body(orderServices.getOrderByOrderId(orderId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrderByUserId(@PathVariable("userId") Long userId){
        return ResponseEntity.ok().body(orderServices.getAllOrdersByUserId(userId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders(){
        return ResponseEntity.ok().body(orderServices.getAllOrders());
    }


}
