package com.vn.edu.iuh.fit.cosmetics_bussiness_project.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnoreProperties("orderItems")
    private Order order;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public OrderItem() {
		super();
	}

	public OrderItem(Order order, String productName, int quantity, double price, Product product) {
		super();
		this.order = order;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.product = product;
	}

	public OrderItem(Order order, String productName, int quantity) {
		super();
		this.order = order;
		this.productName = productName;
		this.quantity = quantity;
	}

	public OrderItem(String productName, int quantity, double price) {
		super();
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}

	public OrderItem(int quantity, double price, Product product) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.product = product;
	}

	public OrderItem(Long id, int quantity, double price) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}

	public OrderItem(Order order, String productName, int quantity, double price) {
		super();
		this.order = order;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}

}