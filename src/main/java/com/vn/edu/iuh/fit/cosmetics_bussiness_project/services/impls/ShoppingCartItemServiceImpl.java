package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.impls;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Product;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.ShoppingCartItem;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.User;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.ProductRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.ShoppingCartItemRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.UserRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

	@Autowired
	private ShoppingCartItemRepository shoppingCartItemRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired JdbcTemplate jdbcTemplate;

	@Override
	public ShoppingCartItem updateShoppingCartItem(Long id, ShoppingCartItem shoppingCartItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteShoppingCartItem(Long id) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public List<ShoppingCartItem> getShoppingCartItemsByUsername(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	@Override
	public List<ShoppingCartItem> getShoppingCartItemsByUsername(String username) {
	    User user = userRepository.findByUsername(username);
	    if (user == null) {
	        throw new IllegalArgumentException("User not found with username: " + username);
	    }

	    return shoppingCartItemRepository.findByUser(user);
	}


	@Override
	public ShoppingCartItem createShoppingCartItem(int quantity, String userId, Long productId) {
		User userCheck = userRepository.findByUsername(userId);
		if(userCheck == null) {
			throw new IllegalArgumentException("User not found with ID: " + userId);
		}
		String sql = "INSERT INTO shopping_cart_item (quantity, product_id, customer_id) VALUES (?, ?, ?)";
		
//		var result = jdbcTemplate.prepareStatement(sql, new String[] {"id"} );
	    
	    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
	    
	    jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
	        ps.setInt(1, quantity);
	        ps.setLong(2, productId);
	        ps.setLong(3, userCheck.getId());
	        return ps;
	    }, keyHolder);
	    
	    Long newId = keyHolder.getKey().longValue();
	    
	    ShoppingCartItem newItem = new ShoppingCartItem();
	    newItem.setId(newId);
	    newItem.setQuantity(quantity);
	    newItem.setUser(userCheck);
	    return newItem;
	}
	
	
//
//	public ShoppingCartItem createShoppingCartItem(ShoppingCartItem shoppingCartItem, Long userId) {
//		// Fetch the user by ID from the database
//		User user = userRepository.findById(userId).orElse(null);
//		if (user == null) {
//			// Handle case where user is not found
//			throw new IllegalArgumentException("User not found with ID: " + userId);
//			// or return null/error response depending on your application's error handling
//			// strategy
//		}
//
//		// Set the user in the shopping cart item
//		shoppingCartItem.setUser(user);
//
//		// Save the shopping cart item
//		return shoppingCartItemRepository.save(shoppingCartItem);
//	}
//
//	@Override
//	public List<ShoppingCartItem> getAllShoppingCartItems() {
//		return shoppingCartItemRepository.findAll();
//	}
//
//	@Override
//	public ShoppingCartItem getShoppingCartItemById(Long id) {
//		return shoppingCartItemRepository.findById(id).orElse(null);
//	}
//
//	@Override
//	public ShoppingCartItem updateShoppingCartItem(Long id, ShoppingCartItem updatedShoppingCartItem) {
//		if (shoppingCartItemRepository.existsById(id)) {
//			updatedShoppingCartItem.setId(id);
//			return shoppingCartItemRepository.save(updatedShoppingCartItem);
//		}
//		return null;
//	}
//
//	@Override
//	public void deleteShoppingCartItem(Long id) {
//		shoppingCartItemRepository.deleteById(id);
//	}
//
//	@Override
//	public List<ShoppingCartItem> getShoppingCartItemsByUsername(String username) {
//		return shoppingCartItemRepository.findByUserUsername(username);
//	}
//
//	@Override
//	public ShoppingCartItem createShoppingCartItem(ShoppingCartItem shoppingCartItem) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
////	
////    @Override
////    public List<ShoppingCartItem> getCartItems(User user) {
////        return shoppingCartItemRepository.findByUser(user);
////    }
////
////    @Override
////    public ShoppingCartItem addCartItem(User user, Long productId, int quantity) {
////        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
////        ShoppingCartItem cartItem = new ShoppingCartItem();
////        cartItem.setUser(user);
////        cartItem.setProduct(product);
////        cartItem.setQuantity(quantity);
////        return shoppingCartItemRepository.save(cartItem);
////    }
////
////    @Override
////    public void removeCartItem(Long cartItemId) {
////        shoppingCartItemRepository.deleteById(cartItemId);
////    }
////
////    @Override
////    public void clearCart(User user) {
////        List<ShoppingCartItem> cartItems = shoppingCartItemRepository.findByUser(user);
////        shoppingCartItemRepository.deleteAll(cartItems);
////    }
//
//	public List<ShoppingCartItem> getCartItems(String username) {
//		return shoppingCartItemRepository.findByUserUsername(username);
//	}
//
//	public ShoppingCartItem addCartItem(String username, ShoppingCartItem item) {
//		// Set the user to the item based on the username
//		// Save the item to the repository
//		return shoppingCartItemRepository.save(item);
//	}
//
//	public void removeCartItem(String username, Long id) {
//		shoppingCartItemRepository.deleteById(id);
//	}
//
//	@Override
//	public void clearCart(User user) {
//		List<ShoppingCartItem> cartItems = shoppingCartItemRepository.findByUser(user);
//		shoppingCartItemRepository.deleteAll(cartItems);
//	}
//
//	@Override
//	public ShoppingCartItem addCartItem(User user, Long productId, int quantity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void removeCartItem(Long cartItemId) {
//		// TODO Auto-generated method stub
//
//	}
	
//    @Autowired
//    private ShoppingCartItemRepository shoppingCartItemRepository;
//
//    @Override
//    public ShoppingCartItem updateShoppingCartItem(Long id, ShoppingCartItem shoppingCartItem) {
//        Optional<ShoppingCartItem> existingItem = shoppingCartItemRepository.findById(id);
//        if (existingItem.isPresent()) {
//            ShoppingCartItem itemToUpdate = existingItem.get();
//            itemToUpdate.setQuantity(shoppingCartItem.getQuantity());
//            itemToUpdate.setProduct(shoppingCartItem.getProduct());
//            itemToUpdate.setUser(shoppingCartItem.getUser());
//            return shoppingCartItemRepository.save(itemToUpdate);
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteShoppingCartItem(Long id) {
//        shoppingCartItemRepository.deleteById(id);
//    }
//
//    @Override
//    public List<ShoppingCartItem> getShoppingCartItemsByUsername(String username) {
//        Long customerId = getCustomerIdByUsername(username);
//        return shoppingCartItemRepository.findByUserId(customerId);
//    }
//
//    private Long getCustomerIdByUsername(String username) {
//        // Implement the logic to get the customer ID by username.
//        // For example, you might need to query a CustomerRepository.
//        return 1L; // Replace with actual implementation
//    }
//    @Override
//    public ShoppingCartItem createShoppingCartItem(ShoppingCartItem shoppingCartItem) {
//        return shoppingCartItemRepository.save(shoppingCartItem);
//    }
}
