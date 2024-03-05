package com.javaexpress.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.entities.Order;
import com.javaexpress.entities.OrderItem;
import com.javaexpress.repository.OrderItemRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderItemService {

	@Autowired
    private OrderItemRepository orderItemRepository;

    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public OrderItem getOrderItemById(Long orderItemId) {
        return orderItemRepository.findById(orderItemId)
                                  .orElseThrow(() -> new EntityNotFoundException("OrderItem not found"));
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem updateOrderItem(Long orderItemId, OrderItem updatedOrderItem) {
        OrderItem existingOrderItem = getOrderItemById(orderItemId);
        existingOrderItem.setProduct(updatedOrderItem.getProduct());
        existingOrderItem.setQuantity(updatedOrderItem.getQuantity());
        existingOrderItem.setPrice(updatedOrderItem.getPrice());
        return orderItemRepository.save(existingOrderItem);
    }

    public void deleteOrderItem(Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }
    
    public List<OrderItem> findOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
    
    public List<OrderItem> findByOrder(Order order) {
        return orderItemRepository.findByOrder(order);
    }

}
