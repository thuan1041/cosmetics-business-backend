package com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}