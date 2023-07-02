package com.example.repository;

import com.example.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    List<Token> findAllByUsernameAndIsValid(@Param("username") String username, @Param("valid") boolean valid);

    Optional<Token> findFirstByUsernameAndToken(@Param("username") String username, @Param("token") String token);

    List<Token> findAllByIsValidAndUsernameIn(@Param("isValid") boolean isValid, @Param("usernames") List<String> usernames);
}
