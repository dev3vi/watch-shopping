package com.example.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.example.dto.RegisterRequest;

public interface AuthenService {
	
	boolean registerUser(HttpServletRequest request, Model model, RegisterRequest registerRequest);
}
