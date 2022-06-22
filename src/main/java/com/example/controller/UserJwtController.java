package com.example.controller;

import com.example.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserJwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/authenticate")
    private String authenticateUser(@RequestBody UserDTO userDTO) throws Exception {
        Authentication authentication = null;
        String jwt = null;
        System.out.println(userDTO.getUsername()+"-"+userDTO.getPassword());
//      Xác thực từ username và password.
//        try {
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                    userDTO.getUsername(),userDTO.getPassword());
//            authentication = authenticationManager.authenticate(authenticationToken);
//            // Nếu không xảy ra exception tức là thông tin hợp lệ
//            // Set thông tin authentication vào Security Context
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            jwt = tokenProvider.createToken(userDTO.getUsername());
//        } catch (Exception exception) {
//            log.error("authentication fail", exception);
//            throw new ServerException("ErrorCode.AUTHEN_ERROR");
//        }
        return jwt;
    }
}
