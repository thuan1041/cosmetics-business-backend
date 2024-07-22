package com.vn.edu.iuh.fit.cosmetics_bussiness_project.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

	public UserProfile(User user, String fullName, String address) {
		super();
		this.user = user;
		this.fullName = fullName;
		this.address = address;
	}

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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserProfile() {
		super();
	}

	public UserProfile(String fullName, String address) {
		super();
		this.fullName = fullName;
		this.address = address;
	}

    // Getters and setters
}