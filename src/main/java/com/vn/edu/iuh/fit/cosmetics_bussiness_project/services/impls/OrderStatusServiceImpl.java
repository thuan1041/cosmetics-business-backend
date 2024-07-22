package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.impls;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.converts.ConvertTime;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.exceptions.ResourceNotFoundException;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderStatus;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.OrderStatusRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.OrderStatusService;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
	
	@Autowired
	private OrderStatusRepository orderStatusRepository;

	@Override
	public OrderStatus updateById(Long id, OrderStatus orderStatus) {
		Optional<OrderStatus> exsitingOrderStatus = orderStatusRepository.findById(id);
		
		if(exsitingOrderStatus != null) {
			OrderStatus updatedOrderStatus = exsitingOrderStatus.get();
			updatedOrderStatus.setStatus(orderStatus.getStatus());
			Date convertedDate = ConvertTime.convertLocalDateTimeToDate(LocalDateTime.now());
			updatedOrderStatus.setStatusDate(convertedDate);
			return orderStatusRepository.save(updatedOrderStatus);
		} else {
			throw new ResourceNotFoundException("OrderStatus with id " + id + " not found");
		}
	}

	@Override
	public List<OrderStatus> getAllOrderStatuses() {
		return orderStatusRepository.findAll();
	}

	@Override
	public OrderStatus getOrderStatusById(Long id) {
		return orderStatusRepository.findById(id).orElse(null);
	}

	@Override
	public OrderStatus saveOrderStatus(OrderStatus orderStatus) {
		return orderStatusRepository.save(orderStatus);
	}

}
