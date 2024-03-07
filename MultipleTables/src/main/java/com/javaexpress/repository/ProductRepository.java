package com.javaexpress.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javaexpress.entities.Category;
import com.javaexpress.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    List<Product> findByCategoryId(Long categoryId);
    
    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.name = :categoryName")
    List<Product> findByCategoryName(@Param("categoryName") String categoryName);
    
//    @Query("""
//    		SELECT DISTINCT p 
//    		FROM Product p 
//    		JOIN p.orderItems oi 
//    		JOIN oi.order o 
//    		JOIN o.user u 
//    		WHERE u.id = :userId""")
//    List<Product> findProductsOrderedByUserId(@Param("userId") Long userId);
    
    List<Product> findByNameContaining(String keyword);
    
    List<Product> findByCategory(Category category);
    
    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.name = :categoryName")
    List<Product> findProductsByCategoryName(@Param("categoryName") String categoryName);


}
