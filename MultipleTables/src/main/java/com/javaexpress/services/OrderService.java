package com.javaexpress.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.entities.Order;
import com.javaexpress.entities.User;
import com.javaexpress.repository.OrderRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {

	@Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                              .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrder(Long orderId, Order updatedOrder) {
        Order existingOrder = getOrderById(orderId);
        existingOrder.setUser(updatedOrder.getUser());
        existingOrder.setOrderItems(updatedOrder.getOrderItems());
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
    
    public List<Order> findOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> findOrdersWithOrderItemsByUserId(Long userId) {
        return orderRepository.findOrdersWithOrderItemsByUserId(userId);
    }
    
    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }
}
