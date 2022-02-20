package com.example.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserAndProduct {

	private String username;
	
	private List<Long> productId;
	
	private Long quantity;
} 
