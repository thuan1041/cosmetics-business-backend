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
//	@Autowired
//	private OrderService orderService;
//
//	@GetMapping
//	public ResponseEntity<List<Order>> getAllOrders() {
//		List<Order> orders = orderService.getAllOrders();
//		return new ResponseEntity<>(orders, HttpStatus.OK);
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id) {
//		Order order = orderService.getOrderById(id);
//		if (order != null) {
//			return new ResponseEntity<>(order, HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	}
//
//	@PostMapping("/")
//	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
//		Order createdOrder = orderService.createOrder(order);
//		return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id, @RequestBody Order order) {
//		Order updatedOrder = orderService.updateOrder(id, order);
//		if (updatedOrder != null) {
//			return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
//		orderService.deleteOrder(id);
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
//
//    @GetMapping("/user/{username}")
//    public ResponseEntity<List<Order>> getOrdersByUsername(@PathVariable("username") String username) {
//        List<Order> orders = orderService.getOrdersByUsername(username);
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }
//}
package com.vn.edu.iuh.fit.cosmetics_bussiness_project.controllers;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.dto.OrderDTO;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.dto.OrderItemDTO;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.dto.ProductDTO;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.dto.StatusDTO;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.dto.UserDTO;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.exceptions.ResourceNotFoundException;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.ApiResponse;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Order;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderItem;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderRequest;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderResponse;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderStatus;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.User;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderStatusRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.OrderService;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	OrderStatusRepository orderStatusRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Order>>> getAllOrders() {
		List<Order> orders = orderService.getAllOrders();
		ApiResponse<List<Order>> response = new ApiResponse<>(HttpStatus.OK.value(), "Orders retrieved successfully",
				orders);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Order>> getOrderById(@PathVariable("id") Long id) {
		Order order = orderService.getOrderById(id);
		if (order != null) {
			ApiResponse<Order> response = new ApiResponse<>(HttpStatus.OK.value(), "Order retrieved successfully",
					order);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			ApiResponse<Order> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Order not found", null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

//    @PostMapping("/")
//    public ResponseEntity<ApiResponse<Order>> createOrder(@RequestBody Order order) {
//        Order createdOrder = orderService.createOrder(order);
//        ApiResponse<Order> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Order created successfully", createdOrder);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//	@PostMapping("/")
//	public ResponseEntity<ApiResponse<OrderDTO>> createOrder(@RequestBody Order order) {
//		Order createdOrder = orderService.createOrder(order);
//		OrderDTO orderDTO = convertToDTO(createdOrder); // Hàm convertToDTO sẽ chuyển đổi Order sang OrderDTO
//		ApiResponse<OrderDTO> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Order created successfully",
//				orderDTO);
//		return new ResponseEntity<>(response, HttpStatus.CREATED);
//	}
//    @PostMapping
//    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
//        // Giả sử rằng order.getUser().getId() chứa ID của User
//        Long userId = order.getUser().getId();
//        User user = userService.findById(userId);
//        if (user == null) {
//            return ResponseEntity.badRequest().body(null); // Hoặc trả về một thông báo lỗi phù hợp
//        }
//        order.setUser(user);
//        Order createdOrder = orderService.createOrder(order);
//        return ResponseEntity.ok(createdOrder);
//    }

	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		Long userId = order.getUser().getId();
		User user = userService.findById(userId);
		if (user == null) {
			return ResponseEntity.badRequest().body(null);
		}
		order.setUser(user);
		Order createdOrder = orderService.createOrder(order);
		return ResponseEntity.ok(createdOrder);
	}

	@PostMapping("/")
	public ResponseEntity<ApiResponse<OrderResponse>> createNewOrder(@RequestBody OrderRequest orderRequest) {
		Long userId = orderRequest.getUserId();
		User user = userService.findById(userId);
		if (user == null) {
			return ResponseEntity.badRequest().body(null);
		}
		OrderResponse orderRepository = orderService.createNewOrder(orderRequest);
		if (user != null) {
			ApiResponse<OrderResponse> response = new ApiResponse<>(HttpStatus.OK.value(), "Order created successfully",
					orderRepository);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			ApiResponse<OrderResponse> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User is null", null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

//	@PostMapping("/")
//	public ResponseEntity<OrderResponse> createNewOrder(@RequestBody OrderRequest orderRequest){
//		Long userId = orderRequest.getUserId();
//		User user = userService.findById(userId);
//		if(user == null) {
//			return ResponseEntity.badRequest().body(null);
//		}
//		OrderResponse createOrder = orderService.createNewOrder(orderRequest);
//		return ResponseEntity.ok(createOrder);
//	}

	public OrderDTO convertToDTO(Order order) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(order.getId());
		orderDTO.setOrderDate(order.getOrderDate());

		if (order.getStatus() != null) {
			orderDTO.setId(order.getId());
		} else {
			orderDTO.setId(null);
		}
		return orderDTO;

//	    UserDTO userDTO = new UserDTO();
//	    userDTO.setId(order.getUser().getId());
//	    userDTO.setUsername(order.getUser().getUsername());
//	    orderDTO.setUser(userDTO);
//	    
//	    StatusDTO statusDTO = new StatusDTO();
//	    statusDTO.setId(order.getStatus().getId());
//	    statusDTO.setStatus(order.getStatus().getStatus());
//	    statusDTO.setStatusDate(order.getOrderDate());
//	    orderDTO.setStatus(statusDTO);
////	    2024-06-14T15:30:00.000+00:00
////	    2024-06-14T15:30:00Z
//
//	    List<OrderItemDTO> orderItemDTOs = new ArrayList<>();
//	    for (OrderItem item : order.getOrderItems()) {
//	        OrderItemDTO itemDTO = new OrderItemDTO();
//	        itemDTO.setId(item.getId());
//	        
//	        ProductDTO productDTO = new ProductDTO();
//	        productDTO.setId(item.getProduct().getId());
//	        productDTO.setName(item.getProduct().getName());
////	        itemDTO.setProduct(item.getProduct());
//
//	        itemDTO.setQuantity(item.getQuantity());
//	        orderItemDTOs.add(itemDTO);
//	    }
//	    orderDTO.setOrderItems(orderItemDTOs);
//
//	    return orderDTO;
	}

	public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
		// Your existing code to create order
		Order order = new Order();
		// Set order status
		OrderStatus status = orderStatusRepository.findById(orderRequest.getStatusId())
				.orElseThrow(() -> new ResourceNotFoundException("OrderStatus not found"));
		order.setStatus(status);
		// Save order
		orderRepository.save(order);
		// Convert to DTO
		OrderDTO orderDTO = convertToDTO(order);
		return ResponseEntity.ok(orderDTO);
	}

//	@PutMapping("/{id}")
//	public ResponseEntity<ApiResponse<OrderResponse>> updateOrder(@PathVariable("id") Long id, @RequestBody OrderRequest order) {
//		OrderResponse updatedOrder = orderService.updateOrder(id, order);
//		if (updatedOrder != null) {
//			ApiResponse<OrderResponse> response = new ApiResponse<>(HttpStatus.OK.value(), "Order updated successfully",
//					updatedOrder);
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} else {
//			ApiResponse<OrderResponse> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Order not found", null);
//			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//		}
//	}
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id, @RequestBody OrderResponse orderResponse) {
        Order updatedOrder = orderService.updateOrder(id, orderResponse);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        }
        return ResponseEntity.notFound().build();
    }
    

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
		orderService.deleteOrder(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/user/{username}")
	public ResponseEntity<ApiResponse<List<Order>>> getOrdersByUsername(@PathVariable("username") String username) {
		List<Order> orders = orderService.getOrdersByUsername(username);
		ApiResponse<List<Order>> response = new ApiResponse<>(HttpStatus.OK.value(), "Orders retrieved successfully",
				orders);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
