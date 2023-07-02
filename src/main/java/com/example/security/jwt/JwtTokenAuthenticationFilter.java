//package com.example.security.jwt;
//
//import io.jsonwebtoken.SignatureException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
//    private final TokenProvider jwtTokenProvider;
//
//    public JwtTokenAuthenticationFilter(TokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest,
//                                    HttpServletResponse httpServletResponse,
//                                    FilterChain chain) throws ServletException, IOException {
//        String authorizationHeader = httpServletRequest.getHeader(Constants.HEADER_AUTHORIZATION);
//        if (StringUtils.isNotBlank(authorizationHeader)) {
//            String token = authorizationHeader.replace(Constants.BEARER_TOKEN_PREFIX, "").trim();
//            if (StringUtils.isNotBlank(token)) {
//                ClaimsToken model = getClaimsToken(token);
//                if (Objects.isNull(model)) {
//                    throw new ExpiredJwtTokenException();
//                }
//
//                List<GrantedAuthority> authorities = new ArrayList<>();
//                for (String role : model.getRoles()) {
//                    authorities.add(new SimpleGrantedAuthority(role));
//                }
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(model.getUsername(), null, authorities);
//                authentication.setDetails(model);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//        chain.doFilter(httpServletRequest, httpServletResponse);
//    }
//
//    private ClaimsToken getClaimsToken(String token) {
//        try {
//            ClaimsToken model = jwtTokenProvider.getInfoFromJWT(token);
//            if (!jwtTokenProvider.isValidToken(model.getUsername(), token)) {
//                throw new SignatureException("Invalid JWT signature");
//            }
//
//            return model;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//}