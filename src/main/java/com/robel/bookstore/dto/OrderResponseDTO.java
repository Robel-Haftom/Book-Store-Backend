package com.robel.bookstore.dto;

import java.util.List;

public record OrderResponseDTO(
        Long orderId,
        String orderStatus,
        String paymentStatus,
        String paymentMethod,
        String totalQuantity,
        String totalAmount,
        String discountAmount,
        String discountPercentage,
        String finalAmount,
        String deliveryDate,
        String notes,
        String orderDate,
        String userName,
        List<OrderItemResponseDTO> orderItems

) {
}
