package com.vn.edu.iuh.fit.cosmetics_bussiness_project.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

    // getters và setters
}