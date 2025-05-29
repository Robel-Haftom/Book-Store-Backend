package com.robel.bookstore.controller.common;

import com.robel.bookstore.dto.OrderResponseDTO;
import com.robel.bookstore.service.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/common/orders")
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable("orderId") Long orderId){
        return ResponseEntity.ok().body(orderServices.getOrderByOrderId(orderId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrderByUserId(@PathVariable("userId") Long userId){
        return ResponseEntity.ok().body(orderServices.getAllOrdersByUserId(userId));
    }


}
