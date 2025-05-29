package com.robel.bookstore.controller.admin;

import com.robel.bookstore.dto.OrderResponseDTO;
import com.robel.bookstore.service.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/admin/orders")
public class AdminOrderController {

    @Autowired
    private OrderServices orderServices;

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @GetMapping()
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders(){
        return ResponseEntity.ok().body(orderServices.getAllOrders());
    }

}
