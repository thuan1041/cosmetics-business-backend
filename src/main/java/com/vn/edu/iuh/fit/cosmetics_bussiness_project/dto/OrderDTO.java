package com.vn.edu.iuh.fit.cosmetics_bussiness_project.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class OrderDTO {
    private Long id;
    private UserDTO user;
    private Date orderDate;
    private StatusDTO status;
    private List<OrderItemDTO> orderItems;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public StatusDTO getStatus() {
		return status;
	}
	public void setStatus(StatusDTO status) {
		this.status = status;
	}
	public List<OrderItemDTO> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}

    // Getters và setters
}