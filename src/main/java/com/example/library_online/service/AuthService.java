package com.example.library_online.service;

import com.example.library_online.dto.AuthLoginRequest;
import com.example.library_online.dto.AuthLoginResponse;
import com.example.library_online.dto.UserRegisterRequest;
import com.example.library_online.entities.User;

public interface AuthService {
    void register(UserRegisterRequest userRegisterRequest);

    AuthLoginResponse login(AuthLoginRequest authLoginRequest);

    User getUsernameFromToken(String token);
}
