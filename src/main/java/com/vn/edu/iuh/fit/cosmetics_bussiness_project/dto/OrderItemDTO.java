package com.vn.edu.iuh.fit.cosmetics_bussiness_project.dto;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Product;

import lombok.Data;
@Data
public class OrderItemDTO {
    private Long id;
    private ProductDTO product;
    private int quantity;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO productDTO) {
		this.product = productDTO;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
}