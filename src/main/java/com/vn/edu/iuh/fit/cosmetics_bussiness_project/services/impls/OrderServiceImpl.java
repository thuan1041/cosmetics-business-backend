//package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.impls;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Order;
//import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderItem;
//import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderStatus;
//import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderItemRepository;
//import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderRepository;
//import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderStatusRepository;
//import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.OrderService;
//
//@Service
//public class OrderServiceImpl implements OrderService {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private OrderItemRepository orderItemRepository;
//
//    @Autowired
//    private OrderStatusRepository orderStatusRepository;
//
//    @Override
//    public Order saveOrder(Order order) {
//        // Retrieve or save the OrderStatus if it's a new instance
//        OrderStatus status = order.getStatus();
//        if (status != null && status.getId() == null) {
//            status = orderStatusRepository.save(status); // Save and get managed instance
//            order.setStatus(status); // Set managed OrderStatus to Order
//        }
//
//        // Now save the Order
//        return orderRepository.save(order);
//    }
//
//    @Override
//    public Order findOrderById(Long id) {
//        return orderRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public List<Order> getAllOrders() {
//        return orderRepository.findAll();
//    }
//
//    @Override
//    public void deleteOrder(Long id) {
//        orderRepository.deleteById(id);
//    }
//
//    @Override
//    public Order updateOrder(Long id, Order updatedOrder) {
//        Order existingOrder = orderRepository.findById(id).orElse(null);
//        if (existingOrder != null) {
//            // Update fields of existingOrder with updatedOrder
//            existingOrder.setUser(updatedOrder.getUser());
//            existingOrder.setOrderItems(updatedOrder.getOrderItems());
//            existingOrder.setOrderDate(updatedOrder.getOrderDate());
//            existingOrder.setStatus(updatedOrder.getStatus());
//
//            // Save and return updated order
//            return orderRepository.save(existingOrder);
//        }
//        return null; // Handle not found scenario appropriately
//    }
//
//    @Override
//    public OrderItem saveOrderItem(OrderItem orderItem) {
//        return orderItemRepository.save(orderItem);
//    }
//
//    @Override
//    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
//        return orderItemRepository.findByOrderId(orderId);
//    }
//
//    @Override
//    public void deleteOrderItem(Long id) {
//        orderItemRepository.deleteById(id);
//    }
//
//    @Override
//    public OrderStatus saveOrderStatus(OrderStatus orderStatus) {
//        return orderStatusRepository.save(orderStatus);
//    }
//
//    @Override
//    public OrderStatus findOrderStatusById(Long id) {
//        return orderStatusRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public List<OrderStatus> getAllOrderStatuses() {
//        return orderStatusRepository.findAll();
//    }
//
//    @Override
//    public void deleteOrderStatus(Long id) {
//        orderStatusRepository.deleteById(id);
//    }
//    @Override
//    public List<OrderItem> findByOrderId(Long orderId){
//    	return orderItemRepository.findByOrderId(orderId);
//    }
//}
package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.impls;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Order;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        if (orderRepository.existsById(id)) {
            order.setId(id);
            return orderRepository.save(order);
        } else {
            return null;
        }
    }

    @Override
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
