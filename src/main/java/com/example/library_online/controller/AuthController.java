package com.example.library_online.controller;

import com.example.library_online.dto.AuthLoginRequest;
import com.example.library_online.dto.AuthLoginResponse;
import com.example.library_online.dto.UserRegisterRequest;
import com.example.library_online.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public void register(@RequestBody UserRegisterRequest userRegisterRequest){
        authService.register(userRegisterRequest);
    }

    @PostMapping("/login")
    public AuthLoginResponse login(AuthLoginRequest authLoginRequest){
        return authService.login(authLoginRequest);
    }

}
