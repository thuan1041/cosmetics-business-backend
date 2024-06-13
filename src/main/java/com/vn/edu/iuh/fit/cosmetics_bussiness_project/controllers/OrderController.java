//package com.vn.edu.iuh.fit.cosmetics_bussiness_project.controllers;
//
//import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Order;
//import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/orders")
//public class OrderController {
//
//    @Autowired
//    private OrderService orderService;
//
//    @PostMapping
//    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
//        Order savedOrder = orderService.saveOrder(order);
//        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
//        Order order = orderService.findOrderById(id);
//        if (order != null) {
//            return new ResponseEntity<>(order, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Order>> getAllOrders() {
//        List<Order> orders = orderService.getAllOrders();
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
//        Order order = orderService.updateOrder(id, updatedOrder);
//        if (order != null) {
//            return new ResponseEntity<>(order, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
//        orderService.deleteOrder(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    // Additional endpoints for OrderItems and OrderStatus
//}
package com.vn.edu.iuh.fit.cosmetics_bussiness_project.controllers;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.ApiResponse;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Order;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> createOrder(@RequestBody Order order) {
        Order newOrder = orderService.createOrder(order);
        ApiResponse<Order> response = new ApiResponse<>(0, "Order created successfully", newOrder);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(id, order);
        ApiResponse<Order> response;
        if (updatedOrder != null) {
            response = new ApiResponse<>(0, "Order updated successfully", updatedOrder);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response = new ApiResponse<>(1, "Order not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            ApiResponse<Void> response = new ApiResponse<>(0, "Order deleted successfully", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            ApiResponse<Void> response = new ApiResponse<>(1, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        ApiResponse<Order> response;
        if (order != null) {
            response = new ApiResponse<>(0, "Order found", order);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response = new ApiResponse<>(1, "Order not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Order>>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        ApiResponse<List<Order>> response = new ApiResponse<>(0, "Orders retrieved successfully", orders);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
