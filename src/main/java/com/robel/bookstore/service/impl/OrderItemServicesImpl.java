package com.robel.bookstore.service.impl;

import com.robel.bookstore.entity.Order;
import com.robel.bookstore.entity.OrderItem;
import com.robel.bookstore.repository.OrderItemRepository;
import com.robel.bookstore.service.OrderItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServicesImpl implements OrderItemServices {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Override
    public void createOrderItem(Order order) {
        for(OrderItem orderItem : order.getOrderItems()){
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        }
    }
}
