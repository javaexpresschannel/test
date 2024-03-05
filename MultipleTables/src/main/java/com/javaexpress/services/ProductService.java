package com.javaexpress.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.entities.Category;
import com.javaexpress.entities.Product;
import com.javaexpress.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product createProduct(Product product) {
        // Additional business logic can be added here, such as validation
        return productRepository.save(product);
    }

	public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = productRepository.findById(productId)
                                                   .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        // Additional business logic can be added here
        return productRepository.save(existingProduct);
    }

	public void deleteProduct(Long productId) {
        // Additional business logic can be added here, such as checking for dependencies
        productRepository.deleteById(productId);
    }
	
	// Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    // Get product by ID
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }
    
    // Get products by category
    public List<Product> getProductsByCategory(Long categoryId) {
        // Implement logic to fetch products by category from the repository
        // For example:
        return productRepository.findByCategoryId(categoryId);
    }
    
    public List<Product> findProductsByCategoryName(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }
    
    public List<Product> searchProductsByName(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }
    
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }
}
