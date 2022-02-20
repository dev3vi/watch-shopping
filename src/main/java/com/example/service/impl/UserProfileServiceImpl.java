package com.example.service.impl;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.common.FileUtils;
import com.example.dto.UserProfileRequest;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService{

	
	
	private final UserRepository userRepository;
	
	private final HttpServletRequest request;

	@Override
	public void updateProfile(String username, UserProfileRequest userProfileRequest) throws IOException {
		// TODO Auto-generated method stub
		
	}
	

	/*
	 * @Override public void updateProfile(String username,
	 * com.example.dto.UserProfileRequest userProfileRequest) throws IOException {
	 * Optional<User> userOptional = this.userRepository.findById(username);
	 * if(!userOptional.isPresent()) throw new
	 * ResponseStatusException(HttpStatus.BAD_REQUEST); User u = userOptional.get();
	 * u.setFullName(userProfileRequest.getFullName());
	 * u.setEmail(userProfileRequest.getEmail());
	 * u.setAvatar(FileUtils.saveAvatar(userProfileRequest.getAvatar(),
	 * this.location, request));
	 * 
	 * this.userRepository.save(u); }
	 */

}
