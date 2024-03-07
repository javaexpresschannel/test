package com.javaexpress.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaexpress.entities.Order;
import com.javaexpress.entities.OrderItem;
import com.javaexpress.entities.Product;
import com.javaexpress.entities.User;
import com.javaexpress.repository.OrderRepository;
import com.javaexpress.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class OrderService {

	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;

    public Long createOrder(Order order) {
    	order.setOrderDate(LocalDateTime.now());
        double totalPrice = 0.0;
        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            if (product.getQuantity() < quantity) {
                throw new IllegalArgumentException("Not enough quantity for product: " + product.getName());
            }
            product.setQuantity(product.getQuantity() - quantity);
            totalPrice += product.getPrice() * quantity;
            productRepository.save(product);
         // Set the Order reference in each OrderItem
         //   item.setOrder(order);
        }
        order.setTotalAmount(totalPrice);
        // Save the order along with OrderItems
        Order save = orderRepository.save(order);
        return save.getId();
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
