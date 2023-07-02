package com.example.security.jwt;

import com.example.dto.TokenModel;
import com.example.dto.UserDTO;
import com.example.dto.response.LoginResponse;
import com.example.security.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserDetailsService userDetailsService;
    private final TokenProvider tokenProvider;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;
    private final PasswordEncoder passwordEncoder;


    public JwtAuthenticationFilter(UserDetailsService userDetailsService,
                                   TokenProvider tokenProvider,
                                   TokenService tokenService,
                                   PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
        this.tokenService = tokenService;
        this.bcryptPasswordEncoder = new BCryptPasswordEncoder();
        this.passwordEncoder = passwordEncoder;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/authenticate", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse) throws AuthenticationException {
        try {
            UserDTO request = new ObjectMapper().readValue(httpServletRequest.getInputStream(), UserDTO.class);
            return authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword(), new ArrayList<>()), userDetailsService);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Authentication authenticate(Authentication auth, UserDetailsService service) throws AuthenticationException {
        UserDetailsService business = service;
        UserDetails user = business.loadUserByUsername(auth.getName());
        if (!passwordEncoder.matches(auth.getCredentials().toString(), user.getPassword())) {
            throw new RuntimeException();
        }
        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest httpServletRequest,
                                            HttpServletResponse httpServletResponse,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        User user = (User) auth.getPrincipal();
        final List<String> authorities = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        ObjectMapper mapper = new ObjectMapper();
        TokenModel tokenModel = tokenProvider.generateToken(auth);
        LoginResponse response = getLoginResponse(user, authorities, tokenModel);
        tokenService.saveToken(response, httpServletRequest);

        httpServletResponse.addHeader("Content-Type", "application/json");
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        httpServletResponse.getWriter().println(mapper.writeValueAsString(response));
    }
    private LoginResponse getLoginResponse(User user, List<String> authorities, TokenModel model) {
        LoginResponse response = new LoginResponse();
        response.setUsername(user.getUsername());
        response.setName(user.getUsername());
        response.setRoles(authorities);
        response.setToken(model.getToken());
        response.setRefreshToken(model.getRefreshToken());
        return response;
    }
}
