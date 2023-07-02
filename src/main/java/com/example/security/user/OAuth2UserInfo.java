package com.example.security.user;

import lombok.Data;

@Data
public class OAuth2UserInfo {
	private String id;
	private String name;
	private String ImageUrl;
	private String email;
	private String type;
}
