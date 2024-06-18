package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.impls;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.ShoppingCartItem;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.User;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.ShoppingCartItemRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.UserRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    private UserRepository userRepository;
    
    public ShoppingCartItem createShoppingCartItem(ShoppingCartItem shoppingCartItem, Long userId) {
        // Fetch the user by ID from the database
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle case where user is not found
            throw new IllegalArgumentException("User not found with ID: " + userId);
            // or return null/error response depending on your application's error handling strategy
        }

        // Set the user in the shopping cart item
        shoppingCartItem.setUser(user);

        // Save the shopping cart item
        return shoppingCartItemRepository.save(shoppingCartItem);
    }

    @Override
    public List<ShoppingCartItem> getAllShoppingCartItems() {
        return shoppingCartItemRepository.findAll();
    }

    @Override
    public ShoppingCartItem getShoppingCartItemById(Long id) {
        return shoppingCartItemRepository.findById(id).orElse(null);
    }

    @Override
    public ShoppingCartItem updateShoppingCartItem(Long id, ShoppingCartItem updatedShoppingCartItem) {
        if (shoppingCartItemRepository.existsById(id)) {
            updatedShoppingCartItem.setId(id);
            return shoppingCartItemRepository.save(updatedShoppingCartItem);
        }
        return null;
    }

    @Override
    public void deleteShoppingCartItem(Long id) {
        shoppingCartItemRepository.deleteById(id);
    }

    @Override
    public List<ShoppingCartItem> getShoppingCartItemsByUsername(String username) {
        return shoppingCartItemRepository.findByUserUsername(username);
    }

	@Override
	public ShoppingCartItem createShoppingCartItem(ShoppingCartItem shoppingCartItem) {
		// TODO Auto-generated method stub
		return null;
	}
}
