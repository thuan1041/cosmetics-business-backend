//package com.vn.edu.iuh.fit.cosmetics_bussiness_project.models;
//
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "shopping_cart_item")
//public class ShoppingCartItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @Column(name = "quantity")
//    private int quantity;
//    
//    @ManyToOne()
//    @JoinColumn(name = "customer_id")
//    private User user;
//
//	public Long getId() {
//		return id;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Product getProduct() {
//		return product;
//	}
//
//	public void setProduct(Product product) {
//		this.product = product;
//	}
//
//	public int getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}
//
//    // Getters and setters
//}
package com.vn.edu.iuh.fit.cosmetics_bussiness_project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shopping_cart_item")
@Getter
@Setter
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User user;

    // No-args constructor required by JPA
    public ShoppingCartItem() {
    }

    // You may want to include additional constructors as needed
    public ShoppingCartItem(Product product, int quantity, User user) {
        this.product = product;
        this.quantity = quantity;
        this.user = user;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
