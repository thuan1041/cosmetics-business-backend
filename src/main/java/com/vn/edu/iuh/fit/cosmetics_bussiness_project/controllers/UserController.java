package com.vn.edu.iuh.fit.cosmetics_bussiness_project.controllers;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.exceptions.ResourceNotFoundException;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.ApiResponse;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.User;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("register")
	public ResponseEntity<ApiResponse<User>> registerUser(@RequestBody User user) {
		ApiResponse<User> response;
		if (userService.findByUsername(user.getUsername()) != null) {
			response = new ApiResponse<>(1, "Username already exists", null);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		User registeredUser = userService.registerUser(user);
		if (registeredUser != null) {
			response = new ApiResponse<>(0, "User registered successfully", registeredUser);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			response = new ApiResponse<>(2, "Registered failed", null);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@GetMapping("/{username}")
	public ResponseEntity<ApiResponse<User>> getUserByUsername(@PathVariable String username) {
		User user = userService.getUserByUsername(username);
		ApiResponse<User> response;
		if (user != null) {
			response = new ApiResponse<>(0, "User found", user);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response = new ApiResponse<>(1, "User not found", null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<User>> loginUser(@RequestBody User loginRequest) {
		User user = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
		ApiResponse<User> response;
		if (user != null) {
			response = new ApiResponse<>(0, "Login Successfully", user);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response = new ApiResponse<>(1, "Login Failed", user);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
	}

}