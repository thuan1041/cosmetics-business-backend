package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.impls;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.exceptions.ResourceNotFoundException;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Order;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderItem;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderItemRequest;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderRequest;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderResponse;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderStatus;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Product;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.User;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.UserProfile;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderItemRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderStatusRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.ProductRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.UserRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.OrderItemService;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.OrderService;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.ProductService;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.UserProfileService;
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

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	private UserRepository userRepository;

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
	@Transactional
	public Order updateOrder(Long orderId, OrderResponse orderResponse) {
		// Lấy thông tin order từ cơ sở dữ liệu
		Order existingOrder = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

		// Update các thông tin của order từ orderResponse
		User user = userRepository.findById(orderResponse.getUserProfile().getId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"User not found with id: " + orderResponse.getUserProfile().getId()));
		user.setPassword(null);
		user.setRole(null);
		existingOrder.setUser(user);

		// Cập nhật các order items, order status, order date,...
		List<OrderItem> items = orderResponse.getOrderItems();
		items.forEach(item -> {

			OrderItem orderItem = orderItemRepository.findById(item.getId()).orElse(null);
			orderItem.setQuantity(item.getQuantity());
			orderItemRepository.save(orderItem);
		});
		
//		OrderStatus orderStatus = orderStatusRepository.findById(orderId).orElse(null);
//		orderStatus.setStatus(orderResponse.getOrderStatus());
//		orderStatus.setStatusDate(convertLocalDateTimeToDate(LocalDateTime.now()));
		existingOrder.setStatus(orderResponse.getOrderStatus());
		existingOrder.getStatus().setStatusDate(convertLocalDateTimeToDate(LocalDateTime.now()));
//		existingOrder.getStatus().setStatus(orderResponse.getOrderStatus().getStatus());
		existingOrder.setOrderDate(orderResponse.getOrderDate());
		existingOrder.getStatus().setId(orderId);
		// Lưu lại order đã được cập nhật
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
		OrderResponse orderResponse = new OrderResponse();

		User user = userService.findById(orderRequest.getUserId());
		UserProfile userProfile = userProfileService.findUserProfileById(user.getId());

		Order order = new Order();
		order.setUser(user);
		order.setOrderDate(orderRequest.getOrderDate());
		orderRepository.save(order);

		List<OrderItem> orderItems = orderRequest.getOrderItems();
		List<OrderItem> items = new ArrayList<OrderItem>();

		orderItems.forEach(item -> {
			OrderItem orderItem = new OrderItem();

			Product product = productService.getProductById(item.getId());
			if (product == null) {
				return;
			}
			orderItem.setOrder(order);
			orderItem.setProduct(product);
			orderItem.setPrice(product.getPrice());
			orderItem.setProductName(product.getName());
			orderItem.setQuantity(item.getQuantity());
			orderItemService.saveOrderItem(orderItem);

			items.add(orderItem);
		});

		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setOrder(order);
		orderStatus.setStatus("CREATED");
		orderStatus.setStatusDate(convertLocalDateTimeToDate(LocalDateTime.now()));
		orderStatusRepository.save(orderStatus);

//		add data in orderResponse and block user info
		orderResponse.setUserProfile((userProfile));
		orderResponse.setOrderItems(items);
		orderResponse.setOrderStatus(orderStatus);
		orderResponse.setOrderDate(orderStatus.getStatusDate());
		return orderResponse;
	}

	public Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
		// Default system zone
		ZoneId defaultZoneId = ZoneId.systemDefault();

		// Convert LocalDateTime to Instant
		Instant instant = localDateTime.atZone(defaultZoneId).toInstant();

		// Convert Instant to Date
		return Date.from(instant);
	}
}
