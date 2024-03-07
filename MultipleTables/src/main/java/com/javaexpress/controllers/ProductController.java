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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.entities.Category;
import com.javaexpress.entities.Product;
import com.javaexpress.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
		Product createdProduct = productService.createProduct(product);
		return ResponseEntity.ok(createdProduct);
	}
	
	@GetMapping("/category/{categoryName}")
	public ResponseEntity<List<Product>> findProductsByCategoryName(@PathVariable String categoryName) {
		List<Product> products = productService.findProductsByCategoryName(categoryName);
		return ResponseEntity.ok(products);
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
		Product product = productService.getProductById(productId);
		return ResponseEntity.ok(product);
	}

	@GetMapping("/name/{name}/id/{id}")
	public Product fetchProduct(@PathVariable Integer id, @PathVariable String name) {
		// return productService.fetch(id);
		return null;
	}

	@PutMapping("/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
		Product product = productService.updateProduct(productId, updatedProduct);
		return ResponseEntity.ok(product);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
		productService.deleteProduct(productId);
		return ResponseEntity.noContent().build();
	}

	

	@GetMapping("/search")
	public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String keyword) {
		List<Product> products = productService.searchProductsByName(keyword);
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/category/id/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        return productService.findByCategory(category);
    }
	
	
	@GetMapping("/category/new/{categoryId}")
    public List<Product> getProductsByCategory1(@PathVariable Long categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

}
