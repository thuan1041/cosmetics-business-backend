package com.vn.edu.iuh.fit.cosmetics_bussiness_project.controllers;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.ApiResponse;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Product;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Product>> addProduct(@RequestBody Product product) {
		Product newProduct = productService.addProduct(product);
		ApiResponse<Product> response = new ApiResponse<>(0, "Product added successfully", newProduct);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		Product updatedProduct = productService.updateProduct(id, product);
		ApiResponse<Product> response;
		if (updatedProduct != null) {
			response = new ApiResponse<>(0, "Product updated successfully", updatedProduct);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response = new ApiResponse<>(1, "Product not found", null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
//        productService.deleteProduct(id);
//        ApiResponse<Void> response = new ApiResponse<>(0, "Product deleted successfully", null);
//        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
//    }
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
		try {
			productService.deleteProduct(id);
			ApiResponse<Void> response = new ApiResponse<>(0, "Product deleted successfully", null);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (RuntimeException e) {
			ApiResponse<Void> response = new ApiResponse<>(1, e.getMessage(), null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id) {
		Product product = productService.getProductById(id);
		ApiResponse<Product> response;
		if (product != null) {
			response = new ApiResponse<>(0, "Product found", product);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response = new ApiResponse<>(1, "Product not found", null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		ApiResponse<List<Product>> response = new ApiResponse<>(0, "Products retrieved successfully", products);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}