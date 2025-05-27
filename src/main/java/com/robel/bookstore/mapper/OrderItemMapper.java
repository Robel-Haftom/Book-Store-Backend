package com.robel.bookstore.mapper;

import com.robel.bookstore.dto.OrderItemResponseDTO;
import com.robel.bookstore.entity.CartItem;
import com.robel.bookstore.entity.OrderItem;

public class OrderItemMapper {

    public static OrderItem mapToOrderItem(CartItem cartItem){
        return OrderItem.builder()
                .quantity(cartItem.getQuantity())
                .unitePrice(cartItem.getUnitePrice())
                .totalPrice(cartItem.getTotalPrice())
                .book(cartItem.getBook())
                .build();
    }

    public static OrderItemResponseDTO mapToOrderItemResponseDTO(OrderItem orderItem){
        return new OrderItemResponseDTO(
                orderItem.getOrderItemId(),
                orderItem.getQuantity(),
                orderItem.getUnitePrice(),
                orderItem.getTotalPrice(),
                orderItem.getBook().getBookTitle(),
                orderItem.getOrder().getUser().getUserName()
        );
    }
}
