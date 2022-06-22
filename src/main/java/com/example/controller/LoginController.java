package com.example.controller;

import javax.validation.Valid;

import com.example.dto.LoginDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

	private final TokenProvider tokenProvider;

	public LoginController(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@GetMapping("/get-user-info")
    public String currentUserNameSimple() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName().toString());
		return authentication.getName();
    }

    @PostMapping("/authenticate")
	public String authenticateUser(@Valid @RequestBody LoginDTO loginDTO){


		return null;

	}
}
