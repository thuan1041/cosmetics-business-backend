package com.vn.edu.iuh.fit.cosmetics_bussiness_project.models;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @Column(name = "order_date")
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus status;

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

    // Getters and setters
}
//package com.vn.edu.iuh.fit.cosmetics_bussiness_project.models;
//
//import jakarta.persistence.*;
//import lombok.*;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Table(name = "customer_order")
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<OrderItem> orderItems;
//
//    @Column(name = "order_date")
//    private Date orderDate;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "status_id")
//    private OrderStatus status;
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
//	public List<OrderItem> getOrderItems() {
//		return orderItems;
//	}
//
//	public void setOrderItems(List<OrderItem> orderItems) {
//		this.orderItems = orderItems;
//	}
//
//	public Date getOrderDate() {
//		return orderDate;
//	}
//
//	public void setOrderDate(Date orderDate) {
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
//    // Constructors, getters, and setters
//}
