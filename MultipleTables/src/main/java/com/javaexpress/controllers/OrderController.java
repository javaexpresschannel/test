package com.javaexpress.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.entities.Order;
import com.javaexpress.entities.User;
import com.javaexpress.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    
	@Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderId, @RequestBody Order updatedOrder) {
        Order order = orderService.updateOrder(orderId, updatedOrder);
        return ResponseEntity.ok(order);
    }
    
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> findOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.findOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{userId}/withItems")
    public ResponseEntity<List<Order>> findOrdersWithOrderItemsByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.findOrdersWithOrderItemsByUserId(userId);
        return ResponseEntity.ok(orders);
    }
    
//    @GetMapping("/user/{userId}")
//    public List<Order> getOrdersByUser(@PathVariable Long userId) {
//        User user = new User();
//        user.setId(userId);
//        return orderService.findByUser(user);
//    }
}

    

