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
@RequestMapping("/api/order-status")
public class OrderStatusController {
	@Autowired
	private OrderStatusService orderStatusService;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Order>>> getAllOrders() {
		List<OrderStatus> orderStatus = orderStatusService.getAllOrderStatuses();
		ApiResponse<List<OrderStatus>> response = new ApiResponse<List<OrderStatus>>(HttpStatus.OK.value(),
				"Order status retrieved successfully", orderStatus);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<OrderStatus>> getOrderStatusById(@PathVariable Long id) {
		OrderStatus orderStatus = orderStatusService.getOrderStatusById(id);
		ApiResponse<OrderStatus> response;
		if (orderStatus != null) {
			response = new ApiResponse<OrderStatus>(0, "Order Status found", orderStatus);
			return new ResponseEntity<ApiResponse<OrderStatus>>(response, HttpStatus.OK);
		}
		response = new ApiResponse<OrderStatus>(1, "Order Status not found", null);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<OrderStatus>> updateOrderStatus(@PathVariable Long id,
			@RequestBody OrderStatus oderStatus) {
		OrderStatus orderStatus = orderStatusService.updateById(id, oderStatus);
		ApiResponse<OrderStatus> response;
		if (orderStatus != null) {
			response = new ApiResponse<OrderStatus>(0, "Order Status successfully", orderStatus);
			return new ResponseEntity<ApiResponse<OrderStatus>>(response, HttpStatus.OK);
		} else {
			response = new ApiResponse<OrderStatus>(1, "Order Status not found", null);
			return new ResponseEntity<ApiResponse<OrderStatus>>(response, HttpStatus.NOT_FOUND);
		}
	}
}