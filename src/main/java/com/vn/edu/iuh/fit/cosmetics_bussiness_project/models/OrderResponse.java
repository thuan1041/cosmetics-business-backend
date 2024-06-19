package com.vn.edu.iuh.fit.cosmetics_bussiness_project.models;

import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
	private UserProfile userProfile;
	private List<OrderItemRequest> orderItemRequests = new ArrayList<OrderItemRequest>();
	private	OrderStatus orderStatus;
}
