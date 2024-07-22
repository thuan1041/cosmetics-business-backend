package com.vn.edu.iuh.fit.cosmetics_bussiness_project.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")

public class OrderUpdateModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("order")
	private List<OrderItem> orderItems = new ArrayList<>();

	private Date orderDate;

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("order")
	private OrderStatus status;

	// Getters và Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public OrderUpdateModel(User user, List<OrderItem> orderItems, Date orderDate) {
		super();
		this.user = user;
		this.orderItems = orderItems;
		this.orderDate = orderDate;
	}

	public OrderUpdateModel(Date orderDate) {
		super();
		this.orderDate = orderDate;
	}

	public OrderUpdateModel() {
		super();
	}

	public OrderUpdateModel(User user, List<OrderItem> orderItems, Date orderDate, OrderStatus status) {
		super();
		this.user = user;
		this.orderItems = orderItems;
		this.orderDate = orderDate;
		this.status = status;
	}
	
}
