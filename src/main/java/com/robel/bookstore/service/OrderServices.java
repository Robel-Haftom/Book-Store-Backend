package com.robel.bookstore.service;

import com.robel.bookstore.dto.OrderResponseDTO;
import com.robel.bookstore.entity.Cart;

import java.util.List;

public interface OrderServices {

    OrderResponseDTO createOrder(Long cartId);

    OrderResponseDTO getOrderByOrderId(Long orderId);

    List<OrderResponseDTO> getAllOrdersByUserId(Long userId);

    List<OrderResponseDTO> getAllOrders();
}
