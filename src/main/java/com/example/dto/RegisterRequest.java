package com.example.dto;

import lombok.Data;

@Data
public class RegisterRequest {
	
	private String username;
	
	private String email;
	
	private String password;
	
	private String fullName;
	
	private Long numberPhone;
	
	private String repeatPassword;

}
