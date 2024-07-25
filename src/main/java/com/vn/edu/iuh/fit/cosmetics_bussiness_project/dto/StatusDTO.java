package com.vn.edu.iuh.fit.cosmetics_bussiness_project.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
@Data
public class StatusDTO {
    private Long id;
    private String status;
    private Date statusDate;
    
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

    // Getters và setters
}