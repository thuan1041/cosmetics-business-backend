package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.impls;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.UserProfile;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories.UserProfileRepository;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.UserProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfile saveUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

	@Override
	public UserProfile findUserProfileById(Long id) {
		return userProfileRepository.findById(id).orElse(null);
	}

    // Implement other methods as needed
}