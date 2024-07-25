package com.vn.edu.iuh.fit.cosmetics_bussiness_project.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

public class OrderResponse {
	private UserProfile userProfile;
	@JsonIgnoreProperties("order")
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	@JsonIgnoreProperties("order")
	private OrderStatus orderStatus;
	private Date orderDate;

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public OrderResponse(UserProfile userProfile, List<OrderItem> orderItems, OrderStatus orderStatus) {
		super();
		this.userProfile = userProfile;
		this.orderItems = orderItems;
		this.orderStatus = orderStatus;
	}

	public OrderResponse() {
		super();
	}

	public OrderResponse(UserProfile userProfile) {
		super();
		this.userProfile = userProfile;
	}

}
