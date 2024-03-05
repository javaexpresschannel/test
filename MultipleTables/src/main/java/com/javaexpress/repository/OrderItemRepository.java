package com.javaexpress.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javaexpress.entities.Order;
import com.javaexpress.entities.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	@Query("SELECT oi FROM OrderItem oi JOIN oi.order o WHERE o.id = :orderId")
	List<OrderItem> findByOrderId(@Param("orderId") Long orderId);
	
	List<OrderItem> findByOrder(Order order);
}
