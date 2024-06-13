package com.vn.edu.iuh.fit.cosmetics_bussiness_project.models;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @Column(name = "order_date")
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus status;

    // Getters and setters
}
