package com.example.service;

import org.springframework.stereotype.Service;

import com.example.dto.ItemRequest;
import com.example.dto.UserAndProduct;

@Service
public interface UserService {

	void updateUser(ItemRequest itemRequest);
}
