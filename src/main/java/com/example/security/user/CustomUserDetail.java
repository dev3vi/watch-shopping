package com.example.security.user;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.constant.UserType;
import com.example.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class CustomUserDetail implements UserDetails, OAuth2User{
	

	private String username;
	
	private String password;

	@Getter
	@Setter
	private String fullName;

	@Getter
	@Setter
	private UserType userType;
	
	@Getter
	@Setter
	private String avatar;
	
	private final Collection<? extends GrantedAuthority> authorities;
	
	@Setter
	private Map<String, Object> attributes;
	
	public static CustomUserDetail createCustomUser(User user,Map<String, Object> attributes) {
		List<GrantedAuthority> authorities = Collections.
				singletonList(new SimpleGrantedAuthority("ROLE_USER"));
		return new CustomUserDetail(user.getUsername(), user.getHashPassword(),
				user.getFullName(), user.getType(), user.getAvatar(),authorities, attributes);
	}
	
	public static CustomUserDetail createCustomUser(User user) {
		List<GrantedAuthority> authorities = Collections.
				singletonList(new SimpleGrantedAuthority(user.getRole().getRoleCode().toString()));
		return new CustomUserDetail(user.getUsername(), user.getHashPassword(),
				user.getFullName(), user.getType(), user.getAvatar(),authorities, null);
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	@Override
	public String getName() {
		return this.fullName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	

	

}
