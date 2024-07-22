package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderStatus;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//public class OrderStatusService {

//    private final OrderStatusRepository orderStatusRepository;
//
//    @Autowired
//    public OrderStatusService(OrderStatusRepository orderStatusRepository) {
//        this.orderStatusRepository = orderStatusRepository;
//    }
//
//    public List<OrderStatus> getAllOrderStatuses() {
//        return orderStatusRepository.findAll();
//    }
//
//    public OrderStatus getOrderStatusById(Long id) {
//        return orderStatusRepository.findById(id).orElse(null);
//    }
//
//    public OrderStatus saveOrderStatus(OrderStatus orderStatus) {
//        return orderStatusRepository.save(orderStatus);
//    }
//
//    public void deleteOrderStatus(Long id) {
//        orderStatusRepository.deleteById(id);
//    }


@Service
public interface OrderStatusService {

	List<OrderStatus> getAllOrderStatuses();

	OrderStatus getOrderStatusById(Long id);

	OrderStatus saveOrderStatus(OrderStatus orderStatus);

	OrderStatus updateById(Long id, OrderStatus orderStatus);
}

//}