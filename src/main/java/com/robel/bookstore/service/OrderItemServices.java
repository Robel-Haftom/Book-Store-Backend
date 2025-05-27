package com.robel.bookstore.service;

import com.robel.bookstore.entity.CartItem;
import com.robel.bookstore.entity.Order;
import com.robel.bookstore.entity.OrderItem;

import java.util.List;

public interface OrderItemServices {

    void createOrderItem(Order order);
}
