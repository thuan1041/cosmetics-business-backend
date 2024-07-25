package com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.ShoppingCartItem;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
//
//    List<ShoppingCartItem> findByUserUsername(String username);
//    List<ShoppingCartItem> findByUser(User user);
    List<ShoppingCartItem> findByUserId(Long userId);

}