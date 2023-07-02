package com.example.service.base;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

public class BaseService {
    public String getCurrentUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (ObjectUtils.isEmpty(authentication) || !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            throw new Exception("User not found");
        }
        // TODO: ThaoLV
        String result = (String) authentication.getPrincipal();
        if (result.startsWith("g")) {
            result = result.substring(1);
        }
        return result;
    }
}
