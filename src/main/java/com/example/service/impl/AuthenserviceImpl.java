package com.example.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import com.example.constant.RoleCode;
import com.example.entity.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.AuthenService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenserviceImpl implements AuthenService{

	
	private final UserRepository userRepository;
	
	private final RoleRepository roleRepository;
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	
	@Override
	@Transactional
	public boolean registerUser(HttpServletRequest request, Model model, com.example.dto.RegisterRequest registerRequest) {
		if(this.userRepository.existsById(registerRequest.getUsername())) {
			model.addAttribute("error", "Username này đã tồn tại!");
			return false;
		}
		
		User user = new User();
		if(StringUtils.hasText(registerRequest.getEmail()) && userRepository.checkEmailsExits(registerRequest.getEmail()).isPresent()) {
			model.addAttribute("error","email"+registerRequest.getEmail()+"đã tồn tại.");
			return false;
		}
		if(registerRequest.getPassword().equals(registerRequest.getRepeatPassword())==false) {
			model.addAttribute("error"," Password không giống nhau");
			return false;
		}
		user.setEmail(registerRequest.getEmail());
		user.setUsername(registerRequest.getUsername());
		user.setHashPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setPhone(registerRequest.getNumberPhone());
		user.setFullName(registerRequest.getFullName());
		user.setRole(this.roleRepository.findRoleByRoleCode(RoleCode.ROLE_USER));
		userRepository.save(user);
	    HttpSession session = request.getSession();
	    session.setAttribute("username", registerRequest.getUsername());
		return true;
	}
}
