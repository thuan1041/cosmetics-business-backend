//package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services;
//
//import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Order;
//import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderItem;
//import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderStatus;
//import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public interface OrderService {
//
//    Order saveOrder(Order order);
//
//    Order findOrderById(Long id);
//
//    List<Order> getAllOrders();
//
//    void deleteOrder(Long id);
//
//    Order updateOrder(Long id, Order updatedOrder);
//
//    // Other methods for OrderItems and OrderStatus
//    OrderItem saveOrderItem(OrderItem orderItem);
//
//    List<OrderItem> getOrderItemsByOrderId(Long orderId);
//
//    void deleteOrderItem(Long id);
//
//    OrderStatus saveOrderStatus(OrderStatus orderStatus);
//
//    OrderStatus findOrderStatusById(Long id);
//
//    List<OrderStatus> getAllOrderStatuses();
//
//    void deleteOrderStatus(Long id);
//
//	List<OrderItem> findByOrderId(Long orderId);
//}
package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order updateOrder(Long id, Order order);
    void deleteOrder(Long id);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
}
