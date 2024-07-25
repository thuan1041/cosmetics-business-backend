package com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//    @Query("SELECT o FROM Order o WHERE o.user.username = :username")
//    List<Order> findByUsername(@Param("username") String username);
    @Query("SELECT DISTINCT o FROM Order o JOIN FETCH o.user u WHERE u.username = :username")
    List<Order> findByUsername(@Param("username") String username);
}