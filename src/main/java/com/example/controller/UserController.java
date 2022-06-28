package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ItemRequest;
import com.example.dto.UserAndProduct;
import com.example.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor 
public class UserController {

	private final UserService userService;
	
	@PostMapping("/add-item")
	public void addItem(@RequestBody ItemRequest itemRequest) {
		userService.updateUser(itemRequest);
	}
}
