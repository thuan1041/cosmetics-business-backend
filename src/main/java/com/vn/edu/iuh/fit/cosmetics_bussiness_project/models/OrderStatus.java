package com.vn.edu.iuh.fit.cosmetics_bussiness_project.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_status")
@Getter
@Setter
@Data
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_name")
    private String statusName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
    // Getters and setters
}
