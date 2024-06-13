package com.vn.edu.iuh.fit.cosmetics_bussiness_project.models;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
@Data
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role")
    private UserRole role;

    public User() {
        super();
    }

    public User(Long id, String username, String password, UserRole role) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

    // Getters and setters
}