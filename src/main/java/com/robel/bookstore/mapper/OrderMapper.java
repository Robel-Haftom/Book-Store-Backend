package com.robel.bookstore.mapper;

import com.robel.bookstore.dto.OrderResponseDTO;
import com.robel.bookstore.entity.Cart;
import com.robel.bookstore.entity.Order;
import com.robel.bookstore.enums.OrderStatus;
import com.robel.bookstore.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderMapper {

    public static Order mapToOrder(Cart cart){
        return Order.builder()
                .totalAmount(cart.getTotalPrice())
                .totalQuantity(cart.getTotalQuantity())
                .user(cart.getUser())
                .discountAmount(cart.getDiscountAmount())
                .discountPercentage(cart.getDiscountPercentage())
                .finalAmount(cart.getFinalAmount())
                .orderStatus(OrderStatus.PENDING)
                .paymentStatus(PaymentStatus.UNPAID)
                .orderDate(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .deliveryDate(LocalDateTime.now().plusHours(24))
                .finalAmount(cart.getTotalPrice() - cart.getDiscountAmount())
                .orderItems(cart.getCartItems().stream().map(OrderItemMapper::mapToOrderItem).toList())
                .build();
    }

    public static OrderResponseDTO mapToOrderResponseDTO(Order order){
        return new OrderResponseDTO(
                order.getOrderId(),
                order.getOrderStatus().toString(),
                order.getPaymentStatus().toString(),
                order.getPaymentMethod().toString(),
                order.getTotalQuantity().toString(),
                order.getTotalAmount().toString(),
                order.getDiscountAmount().toString(),
                order.getDiscountPercentage().toString(),
                order.getFinalAmount().toString(),
                order.getDeliveryDate().format(DateTimeFormatter.ofPattern("MMM/dd/yyyy HH-mm-ss")),
                order.getNotes(),
                order.getOrderDate().format(DateTimeFormatter.ofPattern("MMM/dd/yyyy HH-mm-ss")),
                order.getUser().getUserName(),
                order.getOrderItems().stream().map(OrderItemMapper::mapToOrderItemResponseDTO).toList()
        );
    }
}
