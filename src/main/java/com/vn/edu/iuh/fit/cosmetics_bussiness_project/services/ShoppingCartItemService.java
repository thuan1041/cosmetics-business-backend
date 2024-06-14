package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.ShoppingCartItem;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.ShoppingCartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartItemService {

    private final ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    public ShoppingCartItemService(ShoppingCartItemRepository shoppingCartItemRepository) {
        this.shoppingCartItemRepository = shoppingCartItemRepository;
    }

    public List<ShoppingCartItem> getAllShoppingCartItems() {
        return shoppingCartItemRepository.findAll();
    }

    public Optional<ShoppingCartItem> getShoppingCartItemById(Long id) {
        return shoppingCartItemRepository.findById(id);
    }

    public ShoppingCartItem saveShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        return shoppingCartItemRepository.save(shoppingCartItem);
    }

    public void deleteShoppingCartItem(Long id) {
        shoppingCartItemRepository.deleteById(id);
    }
}