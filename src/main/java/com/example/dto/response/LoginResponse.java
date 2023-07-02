package com.example.dto.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@JsonRootName(value = "user")
public class LoginResponse {
    private String username;
    private String name;
    private Collection<String> roles;
    private boolean isActivated;
    private String token;
    private String refreshToken;
}
