package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dto.RegisterRequest;
import com.example.service.AuthenService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	@GetMapping("/get-user-info")
	@ResponseBody
    public String currentUserNameSimple() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName().toString());
		return authentication.getName();
    }
}
