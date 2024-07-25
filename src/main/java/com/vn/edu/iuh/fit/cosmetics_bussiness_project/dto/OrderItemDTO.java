package com.vn.edu.iuh.fit.cosmetics_bussiness_project.dto;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Product;

import lombok.Data;
@Data
public class OrderItemDTO {
    private Long id;
    private String productName;
    private int quantity;
    private double price;

    // Getters và Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}