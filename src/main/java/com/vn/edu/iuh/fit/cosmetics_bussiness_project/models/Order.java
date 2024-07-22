package com.vn.edu.iuh.fit.cosmetics_bussiness_project.models;

//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Set;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Data
//@Entity
//@Table(name = "orders")
//public class Order {
//	@Id
//	@Column(name = "order_id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	private User user;
//
//	@Column(name = "order_date")
//	private LocalDateTime orderDate;
//
//	@ManyToOne
//	@JoinColumn(name = "status_id")
//	private OrderStatus status;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    @JsonIgnore // Bỏ qua trường này khi chuyển đổi sang JSON
//    private List<OrderItem> orderItems;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public LocalDateTime getOrderDate() {
//		return orderDate;
//	}
//
//	public void setOrderDate(LocalDateTime orderDate) {
//		this.orderDate = orderDate;
//	}
//
//	public OrderStatus getStatus() {
//		return status;
//	}
//
//	public void setStatus(OrderStatus status) {
//		this.status = status;
//	}
//
////	public Set<OrderItem> getOrderItems() {
////		return orderItems;
////	}
////
////	public void setOrderItems(Set<OrderItem> orderItems) {
////		this.orderItems = orderItems;
////	}
//
//	public List<OrderItem> getOrderItems() {
//		return orderItems;
//	}
//
//	public void setOrderItems(List<OrderItem> orderItems) {
//		this.orderItems = orderItems;
//	}
//	
//}
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderStatusRepository;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
public class Order {
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

	public Order(User user, List<OrderItem> orderItems, Date orderDate) {
		super();
		this.user = user;
		this.orderItems = orderItems;
		this.orderDate = orderDate;
	}

	public Order(Date orderDate) {
		super();
		this.orderDate = orderDate;
	}

	public Order() {
		super();
	}

	public Order(User user, List<OrderItem> orderItems, Date orderDate, OrderStatus status) {
		super();
		this.user = user;
		this.orderItems = orderItems;
		this.orderDate = orderDate;
		this.status = status;
	}

//	public void updateFromOrderResponse(OrderResponse orderResponse) {
//		// Cập nhật thông tin User
//		this.setUser(orderResponse.getUserProfile().getUser());
//
//		// Cập nhật danh sách OrderItem
//		List<OrderItem> updatedOrderItems = new ArrayList<>();
//		for (OrderItem orderItem : orderResponse.getOrderItems()) {
//			orderItem.setOrder(this); // Đặt lại Order cho mỗi OrderItem
//			updatedOrderItems.add(orderItem);
//		}
//		this.setOrderItems(updatedOrderItems);
//
//		// Cập nhật OrderDate
//		this.setOrderDate(orderResponse.getOrderDate());
//
//		// Cập nhật OrderStatus
//		OrderStatus newStatus = orderResponse.getOrderStatus();
//		if (newStatus != null) {
//			newStatus.setOrder(this); // Đặt lại Order cho OrderStatus
//			this.setStatus(newStatus);
//		}
//	}
}
