package com.example.security.service;

import java.util.Map;
import java.util.Optional;

import com.example.security.user.CustomUserDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.constant.RoleCode;
import com.example.constant.UserType;
import com.example.entity.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.security.user.OAuth2UserInfo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService{
	
	private final UserRepository userRepository;
	
	private final RoleRepository roleRepository;
	
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	 

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		return this.processAuthenticateUser(userRequest, oAuth2User);
		
	}

//	public Map<String, Object> userDetails(@AuthenticationPrincipal OAuth2User user) {
//		return user.getAttributes();
//	}
	
	private OAuth2User processAuthenticateUser(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		OAuth2UserInfo oAuth2UserInfo = new OAuth2UserInfo();
		oAuth2UserInfo.setId(oAuth2User.getAttribute("sub"));
		oAuth2UserInfo.setName(oAuth2User.getAttribute("name"));
		oAuth2UserInfo.setEmail(oAuth2User.getAttribute("email"));
		oAuth2UserInfo.setImageUrl(oAuth2User.getAttribute("picture"));
		oAuth2UserInfo.setType(oAuth2UserRequest.getClientRegistration().getRegistrationId());
		if(!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
		}
		
		Optional<User> userOptional = this.userRepository.findById(oAuth2UserInfo.getId());
		User user;
		if(userOptional.isPresent()) {
			user = userOptional.get();
			user = updateExistUser(user, oAuth2UserInfo);
		}else {
			user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
		}
		return CustomUserDetail.createCustomUser(user, oAuth2User.getAttributes());
	}
	
	private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
		User user = new User();
		
		user.setUsername(oAuth2UserInfo.getId());
		user.setHashPassword(passwordEncoder.encode("12345"));
		user.setType(UserType.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
		user.setFullName(oAuth2UserInfo.getName());
		user.setAvatar(oAuth2UserInfo.getImageUrl());
		user.setRole(roleRepository.findRoleByRoleCode(RoleCode.ROLE_USER));
		user.setEmail(oAuth2UserInfo.getEmail());
		return userRepository.save(user);
	}

	private User updateExistUser(User user, OAuth2UserInfo oAuth2UserInfo) {
		user.setFullName(oAuth2UserInfo.getName());
		user.setAvatar(oAuth2UserInfo.getImageUrl());
		return this.userRepository.save(user);
	}

}
