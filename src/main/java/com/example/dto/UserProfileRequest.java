package com.example.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UserProfileRequest {

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String fullName;
	
	private MultipartFile avatar;
}
