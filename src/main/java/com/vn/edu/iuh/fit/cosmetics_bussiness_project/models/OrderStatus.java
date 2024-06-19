package com.vn.edu.iuh.fit.cosmetics_bussiness_project.models;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

//@Entity
//@Table(name = "order_status")
//@Getter
//@Setter
//@Data
//public class OrderStatus {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "status_name")
//    private String statusName;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getStatusName() {
//		return statusName;
//	}
//
//	public void setStatusName(String statusName) {
//		this.statusName = statusName;
//	}
//    // Getters and setters
//}
@Entity
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String status;
    private Date statusDate;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderStatus() {
		super();
	}

	public OrderStatus(String status, Date statusDate, Order order) {
		super();
		this.status = status;
		this.statusDate = statusDate;
		this.order = order;
	}

	public OrderStatus(String status, Date statusDate) {
		super();
		this.status = status;
		this.statusDate = statusDate;
	}
    
    // getters and setters
}