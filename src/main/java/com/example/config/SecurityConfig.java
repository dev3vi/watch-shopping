package com.example.config;

import com.example.security.jwt.JwtFilter;
import com.example.security.jwt.TokenProvider;
import com.example.security.service.CustomOauth2UserService;
import com.example.security.service.UserDetailServiceImpl;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailServiceImpl userDetailService;

    private final CustomOauth2UserService customOauth2UserService;

    private final JwtFilter jwtFilter;

    private final TokenProvider tokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.GET, "/css/**")
                .antMatchers("/img/**")
                .antMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/login", "/", "/register", "/api").permitAll()
                .and()
                .oauth2Login()
                .successHandler((request, response, authentication) -> {
//                    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//                    String username = userDetails.getUsername();
//                    tokenProvider.createToken(username);
//                    PrintWriter out = response.getWriter();
//                    out.write(tokenProvider.createToken(username));
//                    out.flush();
//                    out.close();
                    new RedirectServerAuthenticationSuccessHandler("http://localhost:8080/index");
                })
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(customOauth2UserService);

    }
}
