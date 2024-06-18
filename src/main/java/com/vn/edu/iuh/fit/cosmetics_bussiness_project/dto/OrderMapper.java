package com.vn.edu.iuh.fit.cosmetics_bussiness_project.dto;

import java.util.stream.Collectors;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Order;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderItem;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.OrderStatus;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Product;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.User;

public class OrderMapper {
    public static OrderDTO toOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUser(toUserDTO(order.getUser()));
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setStatus(toStatusDTO(order.getStatus()));
        orderDTO.setOrderItems(order.getOrderItems().stream()
                                      .map(OrderMapper::toOrderItemDTO)
                                      .collect(Collectors.toList()));
        return orderDTO;
    }

    public static OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setProduct(toProductDTO(orderItem.getProduct()));
        orderItemDTO.setQuantity(orderItem.getQuantity());
        return orderItemDTO;
    }

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

    public static StatusDTO toStatusDTO(OrderStatus status) {
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setId(status.getId());
        statusDTO.setStatus(status.getStatus());
        statusDTO.setStatusDate(status.getStatusDate());
        return statusDTO;
    }

    public static ProductDTO toProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }
}
