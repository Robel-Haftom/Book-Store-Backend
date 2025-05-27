package com.robel.bookstore.service.impl;

import com.robel.bookstore.dto.OrderResponseDTO;
import com.robel.bookstore.entity.Cart;
import com.robel.bookstore.entity.Order;
import com.robel.bookstore.entity.OrderItem;
import com.robel.bookstore.enums.PaymentMethod;
import com.robel.bookstore.exception.CartNotFoundException;
import com.robel.bookstore.exception.EmptyCartException;
import com.robel.bookstore.exception.OrderNotFoundException;
import com.robel.bookstore.mapper.OrderMapper;
import com.robel.bookstore.repository.CartRepository;
import com.robel.bookstore.repository.OrderItemRepository;
import com.robel.bookstore.repository.OrderRepository;
import com.robel.bookstore.service.OrderItemServices;
import com.robel.bookstore.service.OrderServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServicesImpl implements OrderServices {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemServices orderItemServices;

    @Autowired
    private CartRepository cartRepository;
    @Transactional
    @Override
    public OrderResponseDTO createOrder(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("No cart found with this id: " + cartId));
        if(cart.getCartItems().isEmpty()){
            throw new EmptyCartException("Your cart is empty please add items to your cart");
        }

        Order order = OrderMapper.mapToOrder(cart);

        // these are not from the cart but from other dto
        order.setPaymentMethod(PaymentMethod.CHAPA);
        order.setNotes("i want it quickly please send it as soon as possible");

        Order savedOrder = orderRepository.save(order);
        orderItemServices.createOrderItem(savedOrder);

        cart.getCartItems().clear();
        cart.setDiscountPercentage(0.0);
        cart.setDiscountAmount(0.0);
        cart.setTotalPrice(0.0);
        cart.setFinalAmount(0.0);
        cart.setTotalQuantity(0);
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);

        return OrderMapper.mapToOrderResponseDTO(savedOrder);
    }

        @Override
    public OrderResponseDTO getOrderByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("No order found with this order id: " + orderId));

        return OrderMapper.mapToOrderResponseDTO(order);
    }

    @Override
    public List<OrderResponseDTO> getAllOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUser_UserId(userId);

        return orders.stream().map(OrderMapper::mapToOrderResponseDTO).toList();
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(OrderMapper::mapToOrderResponseDTO).toList();
    }
}
