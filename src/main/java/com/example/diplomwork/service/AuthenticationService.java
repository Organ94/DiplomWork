package com.example.diplomwork.service;

import com.example.diplomwork.dto.request.AuthenticationRQ;
import com.example.diplomwork.dto.response.AuthenticationRS;
import com.example.diplomwork.jwt.JwtTokenProvider;
import com.example.diplomwork.model.User;
import com.example.diplomwork.repository.AuthenticationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    AuthenticationRepository authenticationRepository;

    public AuthenticationRS login(AuthenticationRQ request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(user);
        authenticationRepository.putTokenAndUsername(token, user.getUsername());

        log.info("User {} authentication. JWT: {}", user.getUsername(), token);
        return new AuthenticationRS(token);
    }

    public void logout(String authToken) {
        final String token = authToken.substring(7);
        final String username = authenticationRepository.getUsernameByToken(token);
        log.info("User {} logout. JWT is disabled.", username);
        authenticationRepository.removeTokenAndUsernameByToken(token);
    }
}

