package com.javaexpress.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javaexpress.entities.Order;
import com.javaexpress.entities.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT o FROM Order o JOIN o.user u WHERE u.id = :userId")
	List<Order> findByUserId(@Param("userId") Long userId);

	@Query("SELECT DISTINCT o FROM Order o JOIN FETCH o.orderItems oi JOIN FETCH o.user u WHERE u.id = :userId")
	List<Order> findOrdersWithOrderItemsByUserId(@Param("userId") Long userId);
	
	 List<Order> findByUser(User user);
}
