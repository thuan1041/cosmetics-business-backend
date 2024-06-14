package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.impls;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Order;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderItem;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderStatus;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Product;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderItemRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderStatusRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.ProductRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.OrderService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public Order createOrder(Order order) {
        // Save the main order information
        Order savedOrder = orderRepository.save(order);

        // Save order items
        for (OrderItem item : order.getOrderItems()) {
            item.setOrder(savedOrder);

            // Fetch the product by its ID and set it to the order item
            if (item.getProduct() != null && item.getProduct().getId() != null) {
                Product product = productRepository.findById(item.getProduct().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + item.getProduct().getId()));
                item.setProduct(product);
            }

            orderItemRepository.save(item);
        }

        // Save initial order status
        OrderStatus initialStatus = new OrderStatus();
        initialStatus.setOrder(savedOrder);
        initialStatus.setStatus("CREATED");
        initialStatus.setStatusDate(LocalDateTime.now());
        orderStatusRepository.save(initialStatus);

        return savedOrder;
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
}
