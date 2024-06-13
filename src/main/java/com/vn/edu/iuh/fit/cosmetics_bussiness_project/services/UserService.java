package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.User;

public interface UserService {
    User registerUser(User user);

    User findByUsername(String username);
    
    User loginUser(String username, String password);

	User getUserByUsername(String username);
}