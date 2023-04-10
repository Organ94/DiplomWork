package com.example.diplomwork.controller;

import com.example.diplomwork.dto.request.AuthenticationRQ;
import com.example.diplomwork.dto.response.AuthenticationRS;
import com.example.diplomwork.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {


    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthenticationRS login(@RequestBody @Valid AuthenticationRQ request) {
        return authenticationService.login(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String authToken) {
        authenticationService.logout(authToken);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
