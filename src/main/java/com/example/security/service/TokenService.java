package com.example.security.service;

import com.example.dto.response.LoginResponse;
import com.example.entity.Token;
import com.example.repository.TokenRepository;
import com.example.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j(topic = "APPLICATION")
@Component
@RequiredArgsConstructor
public class TokenService extends BaseService {

    private final TokenRepository tokenRepository;

    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public void saveToken(LoginResponse response, HttpServletRequest httpServletRequest) {
        destroyToken(response.getUsername());
        Token token = new Token(response.getUsername(), response.getToken());
        tokenRepository.save(token);
    }

    public boolean isValidToken(String username, String token) {
        if (username == null || token == null) {
            return false;
        }
        Token tokenObject = tokenRepository.findFirstByUsernameAndToken(username, token).orElse(null);
        return (!Objects.isNull(tokenObject) && tokenObject.isValid());
    }

    public void logout() {
        try {
            destroyToken(getCurrentUser());
        }catch (Exception ex) {
            log.error("[Logout]: Cannot destroy tokens!");
        }
    }

    public void destroyToken(String username) {
        List<Token> oldTokens = tokenRepository.findAllByUsernameAndIsValid(username, true);
        LocalDateTime now = LocalDateTime.now();
        if (oldTokens != null) {
            oldTokens.forEach(t -> {
                t.setValid(false);
            });
            tokenRepository.saveAll(oldTokens);
        }
    }
}
