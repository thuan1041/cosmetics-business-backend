package com.vn.edu.iuh.fit.cosmetics_bussiness_project.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDTO {

	private Long id;
	private String name;
	private BigDecimal price;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal bigDecimal) {
		this.price = bigDecimal;
	}

	// Getters và Setters
}
