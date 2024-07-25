package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Order;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderRequest;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderResponse;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order createOrder(Order order);
//    Order updateOrder(Long id, Order order);
    void deleteOrder(Long id);
    List<Order> getOrdersByUsername(String username);
	OrderResponse createNewOrder(OrderRequest orderRequest);
	Order updateOrder(Long Id, OrderResponse orderResponse);
}
