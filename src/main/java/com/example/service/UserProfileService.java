package com.example.service;

import java.io.IOException;

import com.example.dto.UserProfileRequest;

public interface UserProfileService {

	void updateProfile(String username, UserProfileRequest userProfileRequest) throws IOException;
	
}
