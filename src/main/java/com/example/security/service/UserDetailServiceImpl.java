package com.example.security.service;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.security.user.CustomUserDetail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//load user by username
		User user = this.userRepository.findById(username).orElseThrow(()-> new UsernameNotFoundException("Username chua duoc dang ky"));
		
//		return CustomUserDetail.createCustomUser(user);
		
		
		//load role list
		List<GrantedAuthority> roleList = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getRoleCode().toString()));
		
		//create spring security user
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getHashPassword(),roleList);
	
	}

}
