package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.impls;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Order;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderItem;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderItemRequest;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderRequest;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderResponse;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderStatus;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Product;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.User;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderItemRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderStatusRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.ProductRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.UserRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.OrderItemService;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.OrderService;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.ProductService;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.UserService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderStatusRepository orderStatusRepository;
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserService userService;;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderItemService orderItemService;

//	@Override
//	public Order createOrder(Order order) {
//		// Save the main order information
//		Order savedOrder = orderRepository.save(order);
//
//		// Save order items
//		for (OrderItem item : order.getOrderItems()) {
//			item.setOrder(savedOrder);
//
//			// Fetch the product by its ID and set it to the order item
//			if (item.getProduct() != null && item.getProduct().getId() != null) {
//				Product product = productRepository.findById(item.getProduct().getId()).orElseThrow(
//						() -> new IllegalArgumentException("Product not found with id: " + item.getProduct().getId()));
//				item.setProduct(product);
//			}
//
//			orderItemRepository.save(item);
//		}
//		// Save initial order status
//		OrderStatus initialStatus = new OrderStatus();
//		initialStatus.setOrder(savedOrder);
//		initialStatus.setStatus("CREATED");
//		LocalDateTime currentDateTime = LocalDateTime.now();
//		Date currentDate = java.sql.Timestamp.valueOf(currentDateTime);
//		initialStatus.setStatusDate(currentDate);
//		orderStatusRepository.save(initialStatus);
//
//		return savedOrder;
//	}
	@Override
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderById(Long id) {
		return orderRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));
	}

	@Override
	public Order updateOrder(Long id, Order updatedOrder) {
		Order existingOrder = getOrderById(id);
		existingOrder.setOrderDate(updatedOrder.getOrderDate());
		existingOrder.setOrderItems(updatedOrder.getOrderItems());
		existingOrder.setStatus(updatedOrder.getStatus());
		return orderRepository.save(existingOrder);
	}

	@Override
	public void deleteOrder(Long id) {
		Order order = getOrderById(id);
		orderRepository.delete(order);
	}

	@Override
	public List<Order> getOrdersByUsername(String username) {
		return orderRepository.findByUsername(username);
	}

	@Override
	public OrderResponse createNewOrder(OrderRequest orderRequest) {

		User user = userService.findById(orderRequest.getUserId());
		Order order = new Order();
		order.setUser(user);
		order.setOrderDate(orderRequest.getOrderDate());
		orderRepository.save(order);

		List<OrderItem> orderItems = orderRequest.getOrderItems();

		orderItems.forEach(item -> {
			OrderItem orderItem = new OrderItem();

			Product product = productService.getProductById(item.getId());
			if (product == null) {
				return;
			}
			orderItem.setOrder(order);
			orderItem.setProduct(product);

			orderItem.setPrice(item.getPrice());
			orderItem.setProductName(product.getName());
			orderItem.setQuantity(item.getQuantity());
			orderItemService.saveOrderItem(orderItem);
		});

		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setOrder(order);
		orderStatus.setStatus("CREATED");
		orderStatus.setStatusDate(convertLocalDateTimeToDate(LocalDateTime.now()));
		orderStatusRepository.save(orderStatus);

		
		return null;
	}
	
//	@Override
//	public OrderResponse createNewOrder(OrderRequest orderRequest) {
//
//		User user = userService.findById(orderRequest.getUserId());
//		Order order = new Order();
//		order.setUser(user);
//		order.setOrderDate(orderRequest.getOrderDate());
//		orderRepository.save(order);
//
//		List<OrderItemRequest> orderItems = orderRequest.getOrderItems();
//
//		orderItems.forEach(item -> {
//			OrderItem orderItem = new OrderItem();
//
//			Product product = productService.getProductById(item.getProductId());
//			if (product == null) {
//				return;
//			}
//			orderItem.setOrder(order);
//			orderItem.setProduct(product);
//
//			orderItem.setPrice(item.getPrice());
//			orderItem.setProductName(product.getName());
//			orderItem.setQuantity(item.getQuantity());
//			orderItemService.saveOrderItem(orderItem);
//		});
//
//		OrderStatus orderStatus = new OrderStatus();
//		orderStatus.setOrder(order);
//		orderStatus.setStatus("CREATED");
//		orderStatus.setStatusDate(convertLocalDateTimeToDate(LocalDateTime.now()));
//		orderStatusRepository.save(orderStatus);
//
//		
//		return null;
//	}


	public Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
		// Default system zone
		ZoneId defaultZoneId = ZoneId.systemDefault();

		// Convert LocalDateTime to Instant
		Instant instant = localDateTime.atZone(defaultZoneId).toInstant();

		// Convert Instant to Date
		return Date.from(instant);
	}

//	public List<OrderItem> createOrderItems(OrderRequest orderRequest) {
//		List<OrderItem> orderItems = new ArrayList<>();
//
//		for (OrderItemRequest itemRequest : orderRequest.getOrderItems()) {
//			OrderItem orderItem = new OrderItem();
//			orderItem.setQuantity(itemRequest.getQuantity());
//			orderItem.setPrice(itemRequest.getPrice());
//			orderItems.add(orderItem);
//		}
//
//		return orderItems;
//	}
}
