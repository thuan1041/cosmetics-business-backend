package com.vn.edu.iuh.fit.cosmetics_bussiness_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.ApiResponse;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Order;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderStatus;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.OrderStatusService;

import java.util.List;

@RestController
@RequestMapping("/api/orderStatus")
public class OrderStatusController {
	@Autowired
	private OrderStatusService orderStatusService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<Order>>> getAllOrders() {
		List<OrderStatus> orderStatus = orderStatusService.getAllOrderStatuses();
		ApiResponse<List<OrderStatus>> response = new ApiResponse<List<OrderStatus>>(HttpStatus.OK.value(), "Order status retrieved successfully",
				orderStatus);
		return new ResponseEntity(response, HttpStatus.OK);
	}
}
