package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.UserProfile;

public interface UserProfileService {
    UserProfile saveUserProfile(UserProfile userProfile);
    UserProfile findUserProfileById(Long id);
    // Add more methods as needed
}